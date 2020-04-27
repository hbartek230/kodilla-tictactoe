package com.kodilla.controls;

import com.kodilla.controls.data.winconditions.RowWinChecker;
import com.kodilla.controls.domain.WinConditionChecker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RowWinCheckerTest {

    private WinConditionChecker testing;

    @Before
    public void prepareData() {
        testing = new RowWinChecker();
    }

    @Test
    public void should_returnFalse_when_notMatchAllElements() {
        // given
        FieldState firstEmpty = new FieldState(0, 0, FigureType.EMPTY);
        FieldState cross = new FieldState(0, 1, FigureType.CROSS);
        FieldState secondEmpty = new FieldState(0, 2, FigureType.EMPTY);
        List<FieldState> elements = Arrays.asList(firstEmpty, cross, secondEmpty);

        // when
        boolean result = testing.checkIfWin(elements, cross);

        // then
        Assert.assertFalse(result);
    }

    @Test
    public void should_returnTrue_when_matchAllElements() {
        // given
        FieldState firstCross = new FieldState(0, 0, FigureType.CROSS);
        FieldState secondCross = new FieldState(0, 1, FigureType.CROSS);
        FieldState thirdCross = new FieldState(0, 2, FigureType.CROSS);
        List<FieldState> elements = Arrays.asList(firstCross, secondCross, thirdCross);

        // when
        boolean result = testing.checkIfWin(elements, secondCross);

        // then
        Assert.assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_returnError_when_listIsEmpty() {
        // given
        FieldState changedField = new FieldState(0, 0, FigureType.CROSS);
        List<FieldState> elements = Collections.emptyList();

        // when
        testing.checkIfWin(elements, changedField);
    }
}
