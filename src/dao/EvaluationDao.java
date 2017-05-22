package dao;

import java.util.ArrayList;

import beans.Evaluation;

public interface EvaluationDao {
	public Boolean Evaluate(Evaluation evaluation);

	public Boolean modifyEvaluation(Evaluation evaluation);

	public Boolean deleteEvaluation(Long Id);

	public ArrayList<Evaluation> getMyEvaluation(Long Id, Long limit, Long offset);

}
