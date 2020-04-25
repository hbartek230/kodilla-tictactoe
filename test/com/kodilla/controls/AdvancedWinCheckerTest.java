package com.kodilla.controls;

import com.kodilla.controls.data.winconditions.AdvancedWinChecker;
import com.kodilla.controls.domain.WinConditionChecker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdvancedWinCheckerTest {

    private WinConditionChecker columnChecker;
    private WinConditionChecker rowChecker;
    private WinConditionChecker diagonalChecker;

    private WinConditionChecker testing;

    @Before
    public void prepareData() {
        columnChecker = mock(WinConditionChecker.class);
        rowChecker = mock(WinConditionChecker.class);
        diagonalChecker = mock(WinConditionChecker.class);

        testing = new AdvancedWinChecker(rowChecker, columnChecker, diagonalChecker);
    }

    @Test
    public void should_returnFalse_when_notMatchAnyChecker() {
        // given
        when(rowChecker.checkIfWin(anyListOf(FieldState.class), any(FieldState.class))).thenReturn(false);
        when(columnChecker.checkIfWin(anyListOf(FieldState.class), any(FieldState.class))).thenReturn(false);
        when(diagonalChecker.checkIfWin(anyListOf(FieldState.class), any(FieldState.class))).thenReturn(false);
        List<FieldState> elements = Collections.emptyList();
        FieldState cross = mock(FieldState.class);

        // when
        boolean result = testing.checkIfWin(elements, cross);

        // then
        Assert.assertFalse(result);
    }

    @Test
    public void should_returnFalse_when_oneMatchAnyChecker() {
        // given
        when(rowChecker.checkIfWin(anyListOf(FieldState.class), any(FieldState.class))).thenReturn(false);
        when(columnChecker.checkIfWin(anyListOf(FieldState.class), any(FieldState.class))).thenReturn(true);
        when(diagonalChecker.checkIfWin(anyListOf(FieldState.class), any(FieldState.class))).thenReturn(false);
        List<FieldState> elements = Collections.emptyList();
        FieldState cross = mock(FieldState.class);

        // when
        boolean result = testing.checkIfWin(elements, cross);

        // then
        Assert.assertTrue(result);
    }

    @Test
    public void should_returnFalse_when_allMatchAnyChecker() {
        // given
        when(rowChecker.checkIfWin(anyListOf(FieldState.class), any(FieldState.class))).thenReturn(true);
        when(columnChecker.checkIfWin(anyListOf(FieldState.class), any(FieldState.class))).thenReturn(true);
        when(diagonalChecker.checkIfWin(anyListOf(FieldState.class), any(FieldState.class))).thenReturn(true);
        List<FieldState> elements = Collections.emptyList();
        FieldState cross = mock(FieldState.class);

        // when
        boolean result = testing.checkIfWin(elements, cross);

        // then
        Assert.assertTrue(result);
    }
}
