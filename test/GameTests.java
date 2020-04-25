import com.kodilla.controls.BoardPresenter;
import com.kodilla.controls.BoardSettings;
import com.kodilla.controls.FieldState;
import com.kodilla.controls.FigureType;
import com.kodilla.controls.winconditions.ColumnWinChecker;
import com.kodilla.controls.winconditions.DiagonalWinChecker;
import com.kodilla.controls.winconditions.RowWinChecker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTests {

    private RowWinChecker rwc;
    private ColumnWinChecker cwc;
    private DiagonalWinChecker dwc;
    private List<FieldState> gameBoard;
    private BoardPresenter presenter;

    @Before
    public void prepareTests(){
        rwc = new RowWinChecker();
        cwc = new ColumnWinChecker();
        dwc = new DiagonalWinChecker();
        presenter = new BoardPresenter(new BoardSettings(2, 2));
        gameBoard = new ArrayList<>();

    }

    @Test
    public void testRowWinCheckerWithTwoEmptyFields(){
        FieldState fs1 = new FieldState(0, 0, FigureType.EMPTY);
        FieldState fs2 = new FieldState(0, 1, FigureType.CROSS);
        FieldState fs3 = new FieldState(0, 2, FigureType.EMPTY);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);

        boolean expected = rwc.checkIfWin(gameBoard, fs2);
        Assert.assertFalse(expected);
    }

    @Test
    public void testRowWinCheckerWithOneEmptyField(){
        FieldState fs1 = new FieldState(0, 0, FigureType.EMPTY);
        FieldState fs2 = new FieldState(0, 1, FigureType.CROSS);
        FieldState fs3 = new FieldState(0, 2, FigureType.CROSS);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);

        boolean expected = rwc.checkIfWin(gameBoard, fs2);
        Assert.assertFalse(expected);
    }

    @Test
    public void testRowWinCheckerWithNoEmptyFields(){
        FieldState fs1 = new FieldState(0, 0, FigureType.CROSS);
        FieldState fs2 = new FieldState(0, 1, FigureType.CROSS);
        FieldState fs3 = new FieldState(0, 2, FigureType.CROSS);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);

        boolean expected = rwc.checkIfWin(gameBoard, fs2);
        Assert.assertTrue(expected);
    }

    @Test
    public void testColumnWinCheckerWithTwoEmptyFields(){
        FieldState fs1 = new FieldState(0, 0, FigureType.EMPTY);
        FieldState fs2 = new FieldState(1, 0, FigureType.EMPTY);
        FieldState fs3 = new FieldState(2, 0, FigureType.CROSS);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);

        boolean expected = cwc.checkIfWin(gameBoard, fs3);
        Assert.assertFalse(expected);
    }

    @Test
    public void testColumnWinCheckerWithOneEmptyField(){
        FieldState fs1 = new FieldState(0, 0, FigureType.CROSS);
        FieldState fs2 = new FieldState(1, 0, FigureType.EMPTY);
        FieldState fs3 = new FieldState(2, 0, FigureType.CROSS);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);

        boolean expected = cwc.checkIfWin(gameBoard, fs1);
        Assert.assertFalse(expected);
    }

    @Test
    public void testColumnWinCheckerWithNoEmptyFields(){
        FieldState fs1 = new FieldState(0, 0, FigureType.CROSS);
        FieldState fs2 = new FieldState(1, 0, FigureType.CROSS);
        FieldState fs3 = new FieldState(2, 0, FigureType.CROSS);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);

        boolean expected = cwc.checkIfWin(gameBoard, fs2);
        Assert.assertTrue(expected);
    }

    @Test
    public void testDiagonalWinCheckerWithTwoEmptyFields(){
        FieldState fs1 = new FieldState(0, 0, FigureType.EMPTY);
        FieldState fs2 = new FieldState(1, 1, FigureType.CROSS);
        FieldState fs3 = new FieldState(2, 2, FigureType.EMPTY);
        FieldState fs4 = new FieldState(0, 2, FigureType.EMPTY);
        FieldState fs5 = new FieldState(2, 0, FigureType.EMPTY);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);
        gameBoard.add(fs4);
        gameBoard.add(fs5);

        boolean expected = dwc.checkIfWin(gameBoard, fs2);
        Assert.assertFalse(expected);
    }

    @Test
    public void testDiagonalWinCheckerWithOneEmptyField(){
        FieldState fs1 = new FieldState(0, 0, FigureType.EMPTY);
        FieldState fs2 = new FieldState(1, 1, FigureType.CROSS);
        FieldState fs3 = new FieldState(2, 2, FigureType.CROSS);
        FieldState fs4 = new FieldState(0, 2, FigureType.EMPTY);
        FieldState fs5 = new FieldState(2, 0, FigureType.EMPTY);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);
        gameBoard.add(fs4);
        gameBoard.add(fs5);

        boolean expected = dwc.checkIfWin(gameBoard, fs3);
        Assert.assertFalse(expected);
    }

    @Test
    public void testDiagonalWinCheckerWithNoEmptyFields(){
        FieldState fs1 = new FieldState(0, 0, FigureType.CROSS);
        FieldState fs2 = new FieldState(1, 1, FigureType.CROSS);
        FieldState fs3 = new FieldState(2, 2, FigureType.CROSS);
        FieldState fs4 = new FieldState(0, 2, FigureType.EMPTY);
        FieldState fs5 = new FieldState(2, 0, FigureType.EMPTY);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);
        gameBoard.add(fs4);
        gameBoard.add(fs5);

        boolean expected = dwc.checkIfWin(gameBoard, fs1);
        Assert.assertTrue(expected);
    }

    @Test
    public void testCounterDiagonalWinCheckerWithTwoEmptyFields(){
        FieldState fs1 = new FieldState(0, 0, FigureType.EMPTY);
        FieldState fs2 = new FieldState(1, 1, FigureType.CROSS);
        FieldState fs3 = new FieldState(2, 2, FigureType.EMPTY);
        FieldState fs4 = new FieldState(0, 2, FigureType.EMPTY);
        FieldState fs5 = new FieldState(2, 0, FigureType.EMPTY);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);
        gameBoard.add(fs4);
        gameBoard.add(fs5);

        boolean expected = dwc.checkIfWin(gameBoard, fs2);
        Assert.assertFalse(expected);
    }

    @Test
    public void testCounterDiagonalWinCheckerWithOneEmptyField(){
        FieldState fs1 = new FieldState(0, 0, FigureType.EMPTY);
        FieldState fs2 = new FieldState(1, 1, FigureType.CROSS);
        FieldState fs3 = new FieldState(2, 2, FigureType.EMPTY);
        FieldState fs4 = new FieldState(0, 2, FigureType.CROSS);
        FieldState fs5 = new FieldState(2, 0, FigureType.EMPTY);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);
        gameBoard.add(fs4);
        gameBoard.add(fs5);

        boolean expected = dwc.checkIfWin(gameBoard, fs4);
        Assert.assertFalse(expected);
    }

    @Test
    public void testCounterDiagonalWinCheckerWithNoEmptyFields(){
        FieldState fs1 = new FieldState(0, 0, FigureType.EMPTY);
        FieldState fs2 = new FieldState(1, 1, FigureType.CROSS);
        FieldState fs3 = new FieldState(2, 2, FigureType.EMPTY);
        FieldState fs4 = new FieldState(0, 2, FigureType.CROSS);
        FieldState fs5 = new FieldState(2, 0, FigureType.CROSS);

        gameBoard.add(fs1);
        gameBoard.add(fs2);
        gameBoard.add(fs3);
        gameBoard.add(fs4);
        gameBoard.add(fs5);

        boolean expected = dwc.checkIfWin(gameBoard, fs5);
        Assert.assertTrue(expected);
    }

    @Test
    public void testPrepareGameToShow(){
        List<FieldState> actualGameBoard;

        presenter.prepareGameBoardToShow();
        actualGameBoard = presenter.getGameStatus();

        Assert.assertEquals(4, actualGameBoard.size());
    }
}
