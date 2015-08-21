package it.mgd.checkers.controller;

import it.mgd.checkers.controller.CheckersController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giuliano
 */
public class CheckersControllerTest{
    
    private final CheckersController controller;
    
    public CheckersControllerTest() {
        controller = new CheckersController(new ViewStub(),new ModelStub());
    }
    
    @Before
    public void setUp() {
        controller.onNewGame();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ValidateMove method, of class CheckersController.
     * @throws java.lang.Exception
     */
    @Test
    public void testValidateMove() throws Exception {
        System.out.println("Validate Move Test");
        //Get First of the last row white pawn and move it to valid position
        assertEquals(CheckersController.GetInvalid(), controller.ValidateMoveTest(2,3,1,2));
    }
    
    /**
     * Test of ValidateMove method, of class CheckersController.
     * @throws java.lang.Exception
     */
    @Test
    public void testValidateInvalidMove() throws Exception{
        System.out.println("Validate Invalid Move Test");
        //Get First white pawn and try to move on occupied tile
        assertEquals(CheckersController.GetInvalid(),controller.ValidateMoveTest(0,1,2,2));
    }
        
    /**
     * Test of ValidateMove method, of class CheckersController.
     * @throws java.lang.Exception
     */
    @Test
    public void testValidateCapture() throws Exception{
        System.out.println("Validate Capture Test");
        controller.onClick(1, 2);
        controller.onClick(2, 3);
        controller.onClick(4, 5);
        controller.onClick(3, 4);
        //Capture move
        assertEquals(CheckersController.GetCapture(),controller.ValidateMoveTest(2,3,4,5));
    }
    
}
