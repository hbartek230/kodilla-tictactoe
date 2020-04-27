package com.kodilla.controls;

import com.kodilla.controls.domain.WinConditionChecker;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class BoardPresenterTest {

    private final BoardSettings boardSettings = mock(BoardSettings.class);
    private final WinConditionChecker checker = mock(WinConditionChecker.class);
    private final ComputerControl computerControl = mock(ComputerControl.class);
    private final BoardContract.View view = mock(BoardContract.View.class);

    private BoardPresenter testing;

    @Before
    public void prepareTest() {
        testing = new BoardPresenter(boardSettings, checker, computerControl);
        when(boardSettings.getColumnCount()).thenReturn(3);
        when(boardSettings.getRowCount()).thenReturn(3);
    }

    @Test
    public void should_showGameBoardWithEmptyFields_when_setView() {
        // given
        List<FieldState> expectedList = Arrays.asList(
                new FieldState(0, 0, FigureType.EMPTY),
                new FieldState(0, 1, FigureType.EMPTY),
                new FieldState(0, 2, FigureType.EMPTY),
                new FieldState(1, 0, FigureType.EMPTY),
                new FieldState(1, 1, FigureType.EMPTY),
                new FieldState(1, 2, FigureType.EMPTY),
                new FieldState(2, 0, FigureType.EMPTY),
                new FieldState(2, 1, FigureType.EMPTY),
                new FieldState(2, 2, FigureType.EMPTY)
        );

        // when
        testing.setView(view);

        //then
        verify(view).fillGameBoard(expectedList);
    }

    @Test
    public void should_showUserMoveInfo_when_startNewGame() {
        // given

        // when
        testing.setView(view);

        //then
        verify(view).showInfoAboutUserMove();
    }

    @Test
    public void should_changeFieldInSelectedRowAndColumn() {
        // given
        when(computerControl.selectComputerFigure(anyListOf(FieldState.class)))
                .thenReturn(new FieldState(2, 1, FigureType.CIRCLE));
        int rowClicked = 0;
        int columnClicked = 2;
        FieldState expected = new FieldState(rowClicked, columnClicked, FigureType.CROSS);

        List<FieldState> expectedList = Arrays.asList(
                new FieldState(0, 0, FigureType.EMPTY),
                new FieldState(0, 1, FigureType.EMPTY),
                expected,
                new FieldState(1, 0, FigureType.EMPTY),
                new FieldState(1, 1, FigureType.EMPTY),
                new FieldState(1, 2, FigureType.EMPTY),
                new FieldState(2, 0, FigureType.EMPTY),
                new FieldState(2, 1, FigureType.CIRCLE),
                new FieldState(2, 2, FigureType.EMPTY)
        );

        // when
        testing.setView(view);
        testing.selectedFieldByUser(rowClicked, columnClicked);

        // then
        verify(view, times(3)).fillGameBoard(expectedList);
    }
}

