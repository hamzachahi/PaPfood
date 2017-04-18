package dao;

import beans.Evaluation;

public interface EvaluationDao {
	public Boolean Evaluate(Evaluation evaluation);

	public Boolean modifyEvaluation(Evaluation evaluation);

	public Boolean deleteEvaluation(Evaluation evaluation);

}
