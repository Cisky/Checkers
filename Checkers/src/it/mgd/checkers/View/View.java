package it.mgd.checkers.View;

import it.mgd.checkers.controller.Controller;
import it.mgd.checkers.model.Model;

/**
 *
 * @author Francesco Battipaglia
 */

public interface View
{
	Model GetModel();

	void SetController(Controller controller);
}