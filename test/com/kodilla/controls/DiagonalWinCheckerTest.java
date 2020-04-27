package com.kodilla.controls;

import com.kodilla.controls.data.winconditions.DiagonalWinChecker;
import com.kodilla.controls.domain.WinConditionChecker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiagonalWinCheckerTest {

    private WinConditionChecker testing;

    @Before
    public void prepareData() {
        testing = new DiagonalWinChecker();
    }

    @Test
    public void should_returnFalse_when_notMatchAllElementsInDiagonal() {
        // given
        FieldState firstEmpty = new FieldState(0, 0, FigureType.EMPTY);
        FieldState cross = new FieldState(1, 1, FigureType.CROSS);
        FieldState secondEmpty = new FieldState(2, 2, FigureType.EMPTY);
        FieldState firstEmptyInCounterDiagonal = new FieldState(0, 2, FigureType.EMPTY);
        FieldState secondEmptyInCounterDiagonal = new FieldState(2, 0, FigureType.EMPTY);
        List<FieldState> elements = Arrays.asList(firstEmpty, cross, secondEmpty, firstEmptyInCounterDiagonal,
                secondEmptyInCounterDiagonal);

        // when
        boolean result = testing.checkIfWin(elements, cross);

        // then
        Assert.assertFalse(result);
    }

    @Test
    public void should_returnFalse_when_notMatchAllElementsInCounterDiagonal() {
        // given
        FieldState firstEmpty = new FieldState(0, 0, FigureType.EMPTY);
        FieldState secondEmpty = new FieldState(1, 1, FigureType.EMPTY);
        FieldState thirdEmpty = new FieldState(2, 2, FigureType.EMPTY);
        FieldState cross = new FieldState(0, 2, FigureType.CROSS);
        FieldState secondEmptyInCounterDiagonal = new FieldState(2, 0, FigureType.EMPTY);
        List<FieldState> elements = Arrays.asList(firstEmpty, secondEmpty, thirdEmpty, cross,
                secondEmptyInCounterDiagonal);

        // when
        boolean result = testing.checkIfWin(elements, cross);

        // then
        Assert.assertFalse(result);
    }

    @Test
    public void should_returnTrue_when_matchAllElementsInDiagonal() {
        // given
        FieldState firstCross = new FieldState(0, 0, FigureType.CROSS);
        FieldState secondCross = new FieldState(1, 1, FigureType.CROSS);
        FieldState thirdCross = new FieldState(2, 2, FigureType.CROSS);
        FieldState firstEmptyInCounterDiagonal = new FieldState(0, 2, FigureType.EMPTY);
        FieldState secondEmptyInCounterDiagonal = new FieldState(2, 0, FigureType.EMPTY);
        List<FieldState> elements = Arrays.asList(firstCross, secondCross, thirdCross, firstEmptyInCounterDiagonal,
                secondEmptyInCounterDiagonal);

        // when
        boolean result = testing.checkIfWin(elements, thirdCross);

        // then
        Assert.assertTrue(result);
    }

    @Test
    public void should_returnFalse_when_matchAllElementsInCounterDiagonal() {
        // given
        FieldState firstEmpty = new FieldState(0, 0, FigureType.EMPTY);
        FieldState firstCross = new FieldState(1, 1, FigureType.CROSS);
        FieldState secondEmpty = new FieldState(2, 2, FigureType.EMPTY);
        FieldState secondCross = new FieldState(0, 2, FigureType.CROSS);
        FieldState thirdCross = new FieldState(2, 0, FigureType.CROSS);
        List<FieldState> elements = Arrays.asList(firstEmpty, firstCross, secondEmpty, secondCross, thirdCross);

        // when
        boolean result = testing.checkIfWin(elements, thirdCross);

        // then
        Assert.assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_returnError_when_listIsEmpty(){
        // given
        FieldState changedField = new FieldState(0, 0, FigureType.CROSS);
        List<FieldState> elements = Collections.emptyList();

        // when
        testing.checkIfWin(elements, changedField);
    }
}
