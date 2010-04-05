package weka.test;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Properties;

import weka.associations.gsp.Element;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AddClassification;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

public class ArrangedProgrammaticUse {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		//A.分類器を作って、/weka/test/weather_result.modelに保存
		/*
        //Step 1: Express the problem with features
        FastVector attribute = buildAttribute();

         //Step 2: Train a Classifier 
        Instances isTrainingSet = trainClassifier(attribute);

        //Step 3: Test the classifier
        Classifier cModel = evaluateClassifier(isTrainingSet);

        //Step 3.5: Visualize the classifier
        vizualizeClassifier(cModel);

        //Step 4: Serialize
        Serialization(cModel);
		 */

		//B. /weka/test/weather_result.modelの分類器をつかって、新しいデータのカテゴリを予測（分類）する。
		/*
        ArrangedProgrammaticUse.predict(args);
		 */

	}

	public static void predict(String[] args) throws Exception {

		//Step 5: Deserialize
		Classifier tModel = Deserialization();

		//Step 6 = 1: Express the problem with features
		FastVector attribute = buildAttribute();

		//Step 7 = 2: Train a Classifier 
		Instances isTrainingSet = trainClassifier(attribute);


		//Step 6.5: TestDataInput
		Instance iUse = new Instance(5);
		//iUse.setValue((Attribute)attribute.elementAt(0), "sunny");
		iUse.setValue((Attribute)attribute.elementAt(1), 85);
		iUse.setValue((Attribute)attribute.elementAt(2), 85);
		iUse.setValue((Attribute)attribute.elementAt(3), "FALSE");
		//iUse.setValue((Attribute)attribute.elementAt(4), "yes");

		iUse.setDataset(isTrainingSet);
		//instancesの属性を指定すると、その要素をすべて削除する。
		//isTrainingSet.deleteAttributeAt(0);http://twitter.com/home
		//isTrainingSet.delete(1);
		System.out.println(isTrainingSet.toString());
		System.out.println();

		double[] fDistribution = tModel.distributionForInstance(iUse);
		//fDistribution[0] is the probability of being �gyes�h 
		//fDistribution[1] is the probability of being �gno�h 
		System.out.println("YESの確率" + fDistribution[0]);
		System.out.println("NOの確率" + fDistribution[1]);

		double preClass = tModel.classifyInstance(iUse);
		System.out.println("[0]yes / [1]no  prediction class:" + preClass);
		System.out.println();

		System.out.println(attribute.toArray()[0]);
		System.out.println((Attribute)attribute.elementAt(0));


		System.out.println(iUse.attribute(0).value(0));

		System.out.println(iUse.attribute(4).name() + " -> " + iUse.attribute(4).value((int)preClass) + "(" + new BigDecimal(String.valueOf(fDistribution[(int)preClass] * 100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "%)");


		/*
        String nameOfDataset = "J48ClassifierProblem";
        //Create an empty training set 
        //10�̓L���p�V�e�B
        Instances iFilterUse = new Instances(nameOfDataset, attribute, 10);           
        //Set class index
        //�ǂ̑����𒲂ׂ邩�H�@Class�̎w��
        iFilterUse.setClassIndex(4);
        iFilterUse = isTrainingSet;
        iFilterUse.add(iUse);


        //setup filter
        //Instances dataWithPred = null;
        AddClassification filter = new AddClassification();
        filter.setOutputClassification(true);
        filter.setOutputDistribution(true);
        filter.setOutputErrorFlag(true);

        //add prediction
        filter.setClassifier(tModel);
        filter.setInputFormat(iFilterUse);
        Instances testPred = Filter.useFilter(iFilterUse, filter);


        //Test the model
        Evaluation eTest = new Evaluation(isTrainingSet);
        eTest.evaluateModel(tModel, isTrainingSet);
        System.out.println(eTest.toSummaryString());        

        System.out.println(testPred);
		 */



		/*
        String nameOfDataset = "J48ClassifierProblem";

        //Create an empty training set 
        //10�̓L���p�V�e�B
        Instances i22Use = new Instances(nameOfDataset, attribute, 10);           

        //Set class index
        //�ǂ̑����𒲂ׂ邩�H�@Class�̎w��
        i22Use.setClassIndex(4);

        //Create the Instance
        Instance iExample = new Instance(5);
        iExample.setValue((Attribute)attribute.elementAt(0), "sunny");
        iExample.setValue((Attribute)attribute.elementAt(1), 85);
        iExample.setValue((Attribute)attribute.elementAt(2), 85);
        iExample.setValue((Attribute)attribute.elementAt(3), "FALSE");
        iExample.setValue((Attribute)attribute.elementAt(4), "no");

        i22Use.add(iExample);
		 */



	}

	/*
	 * Step1: Express the problem with features
	 */
	private static FastVector buildAttribute(){
		//Add class atrribute.
		FastVector classOutlookValue = new FastVector(3);
		classOutlookValue.addElement("sunny");
		classOutlookValue.addElement("overcast");
		classOutlookValue.addElement("rainy");
		//Build attribute from classValue.
		Attribute Attribute1 = new Attribute("outlook", classOutlookValue);        

		Attribute Attribute2 = new Attribute("temperature");

		Attribute Attribute3 = new Attribute("humidity");

		FastVector classWindyValue = new FastVector(2);
		classWindyValue.addElement("TRUE");
		classWindyValue.addElement("FALSE");
		Attribute Attribute4 = new Attribute("windy", classWindyValue);

		FastVector classPlayValue = new FastVector(2);
		classPlayValue.addElement("yes");
		classPlayValue.addElement("no");
		Attribute ClassAttribute = new Attribute("play", classPlayValue);

		//Create vector of attributes.
		//Declare the feature vector. Add attribute for each attribute.
		FastVector attribute = new FastVector(5);
		attribute.addElement(Attribute1);
		attribute.addElement(Attribute2);
		attribute.addElement(Attribute3);
		attribute.addElement(Attribute4);
		attribute.addElement(ClassAttribute);

		return attribute;
	}

	/*
	 * Step2: Train a Classifier
	 */

	private static Instances trainClassifier(FastVector attribute) throws Exception{
		String nameOfDataset = "J48ClassifierProblem";

		//Create an empty training set 
		//10�̓L���p�V�e�B
		Instances isTrainingSet = new Instances(nameOfDataset, attribute, 10);           

		//Set class index
		//�ǂ̑����𒲂ׂ邩�H�@Class�̎w��
		isTrainingSet.setClassIndex(4);

		//Create the Instance
		Instance iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "sunny");
		iExample.setValue((Attribute)attribute.elementAt(1), 85);
		iExample.setValue((Attribute)attribute.elementAt(2), 85);
		iExample.setValue((Attribute)attribute.elementAt(3), "FALSE");
		iExample.setValue((Attribute)attribute.elementAt(4), "no");

		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "sunny");
		iExample.setValue((Attribute)attribute.elementAt(1), 80);
		iExample.setValue((Attribute)attribute.elementAt(2), 90);
		iExample.setValue((Attribute)attribute.elementAt(3), "TRUE");
		iExample.setValue((Attribute)attribute.elementAt(4), "no");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "overcast");
		iExample.setValue((Attribute)attribute.elementAt(1), 88);
		iExample.setValue((Attribute)attribute.elementAt(2), 86);
		iExample.setValue((Attribute)attribute.elementAt(3), "FALSE");
		iExample.setValue((Attribute)attribute.elementAt(4), "yes");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "rainy");
		iExample.setValue((Attribute)attribute.elementAt(1), 70);
		iExample.setValue((Attribute)attribute.elementAt(2), 96);
		iExample.setValue((Attribute)attribute.elementAt(3), "FALSE");
		iExample.setValue((Attribute)attribute.elementAt(4), "yes");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "rainy");
		iExample.setValue((Attribute)attribute.elementAt(1), 68);
		iExample.setValue((Attribute)attribute.elementAt(2), 80);
		iExample.setValue((Attribute)attribute.elementAt(3), "FALSE");
		iExample.setValue((Attribute)attribute.elementAt(4), "yes");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "rainy");
		iExample.setValue((Attribute)attribute.elementAt(1), 65);
		iExample.setValue((Attribute)attribute.elementAt(2), 70);
		iExample.setValue((Attribute)attribute.elementAt(3), "TRUE");
		iExample.setValue((Attribute)attribute.elementAt(4), "no");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "overcast");
		iExample.setValue((Attribute)attribute.elementAt(1), 64);
		iExample.setValue((Attribute)attribute.elementAt(2), 65);
		iExample.setValue((Attribute)attribute.elementAt(3), "TRUE");
		iExample.setValue((Attribute)attribute.elementAt(4), "yes");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "sunny");
		iExample.setValue((Attribute)attribute.elementAt(1), 72);
		iExample.setValue((Attribute)attribute.elementAt(2), 95);
		iExample.setValue((Attribute)attribute.elementAt(3), "FALSE");
		iExample.setValue((Attribute)attribute.elementAt(4), "no");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "sunny");
		iExample.setValue((Attribute)attribute.elementAt(1), 69);
		iExample.setValue((Attribute)attribute.elementAt(2), 70);
		iExample.setValue((Attribute)attribute.elementAt(3), "FALSE");
		iExample.setValue((Attribute)attribute.elementAt(4), "yes");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "rainy");
		iExample.setValue((Attribute)attribute.elementAt(1), 75);
		iExample.setValue((Attribute)attribute.elementAt(2), 80);
		iExample.setValue((Attribute)attribute.elementAt(3), "FALSE");
		iExample.setValue((Attribute)attribute.elementAt(4), "yes");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "sunny");
		iExample.setValue((Attribute)attribute.elementAt(1), 75);
		iExample.setValue((Attribute)attribute.elementAt(2), 70);
		iExample.setValue((Attribute)attribute.elementAt(3), "TRUE");
		iExample.setValue((Attribute)attribute.elementAt(4), "yes");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "overcast");
		iExample.setValue((Attribute)attribute.elementAt(1), 72);
		iExample.setValue((Attribute)attribute.elementAt(2), 90);
		iExample.setValue((Attribute)attribute.elementAt(3), "TRUE");
		iExample.setValue((Attribute)attribute.elementAt(4), "yes");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "overcast");
		iExample.setValue((Attribute)attribute.elementAt(1), 81);
		iExample.setValue((Attribute)attribute.elementAt(2), 75);
		iExample.setValue((Attribute)attribute.elementAt(3), "FALSE");
		iExample.setValue((Attribute)attribute.elementAt(4), "yes");
		isTrainingSet.add(iExample);

		iExample = new Instance(5);
		iExample.setValue((Attribute)attribute.elementAt(0), "rainy");
		iExample.setValue((Attribute)attribute.elementAt(1), 71);
		iExample.setValue((Attribute)attribute.elementAt(2), 91);
		iExample.setValue((Attribute)attribute.elementAt(3), "TRUE");
		iExample.setValue((Attribute)attribute.elementAt(4), "no");
		isTrainingSet.add(iExample);

		return isTrainingSet;
	}

	/*
	 * Step3: Test the classifier
	 */
	private static Classifier evaluateClassifier(Instances isTrainingSet) throws Exception{

		//Create a J48 classifier
		Classifier cModel = (Classifier)new J48();
		//J48r cModel = new J48();
		cModel.buildClassifier(isTrainingSet);

		//Test the model
		Evaluation eTest = new Evaluation(isTrainingSet);
		eTest.evaluateModel(cModel, isTrainingSet);

		//Print the resule a la Weka explorer:
		System.out.println(eTest.toSummaryString());
		System.out.println(eTest.toClassDetailsString());
		System.out.println(eTest.toCumulativeMarginDistributionString());
		System.out.println(eTest.toMatrixString());
		System.out.println(eTest.toClassDetailsString());
		System.out.println(cModel);

		//Get the confusion matrix
		double[][] cmMatrix = eTest.confusionMatrix();

		return cModel;
	}

	/*
	 * Step3.5: Visualize the Classifier
	 */

	private static void vizualizeClassifier(Classifier cModel) throws Exception{
		// display classifier
		//cModel.buildClassifier(isTrainingSet);
		final javax.swing.JFrame jf = 
			new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
		jf.setSize(500,400);
		jf.getContentPane().setLayout(new BorderLayout());
		TreeVisualizer tv = new TreeVisualizer(null, ((J48)cModel).graph(),
				new PlaceNode2());
		jf.getContentPane().add(tv, BorderLayout.CENTER);
		jf.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				jf.dispose();
			}
		});

		jf.setVisible(true);
		tv.fitToScreen(); 
	}

	/*
	 * Step4:�@Serialization 
	 */
	private static void Serialization(Classifier cModel) throws Exception{

		//serialize model
		weka.core.SerializationHelper.write("src/weka/test/weather_result.model", cModel);

		System.out.println("Serialization...end");

		return;

	}

	/*
	 * Step5: Desrialization
	 */
	private static Classifier Deserialization() throws Exception {

		//deserialize model
		Classifier cls = (Classifier) weka.core.SerializationHelper.read("src/weka/test/weather_result.model");

		return cls;
	}
}
