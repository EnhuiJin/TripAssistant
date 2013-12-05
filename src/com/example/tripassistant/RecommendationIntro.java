package com.example.tripassistant;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import android.os.Environment;

public class RecommendationIntro {
	RecommendationIntro() {

	};

	public List<RecommendedItem> ItemBasedRecommender(long userID, int size) {
		List<RecommendedItem> recommendations = null;
		try {
			// DataModel model = new FileDataModel(new
			// File("dataSource/data.txt"));//date model File-based

			DataModel model = new GenericBooleanPrefDataModel(
					GenericBooleanPrefDataModel.toDataMap(new FileDataModel(
							new File(Environment.getExternalStorageDirectory()
									+ "/text.txt"))));
			ItemSimilarity similarity = new LogLikelihoodSimilarity(model); // item similarity																			
			Recommender recommender = new GenericItemBasedRecommender(model,
					similarity);// construct recommendation engine
			recommendations = recommender.recommend(userID, size);// get recommendation results
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return recommendations;
	}
}
