package dao;

import java.util.ArrayList;

import beans.Evaluation;

public interface EvaluationDao {
	public Boolean Evaluate(Long IdPerson, Long Jury, Double Note, String Comments);

	public Boolean modifyEvaluation(Long Id, Double Note, String Comments);

	public Boolean deleteEvaluation(Long Id);

	public ArrayList<Evaluation> getMyEvaluation(Long Id, Long limit, Long offset);

	public Long selectNbreEvaluationByIdPerson(Long IdPerson);

	public Double selectMoyenneEvaluationByIdPerson(Long IdPerson);
}
