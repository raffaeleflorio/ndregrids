package io.github.raffaeleflorio.ndregrids.square;

import io.github.raffaeleflorio.ndregrids.Grid;

import java.util.List;

public final class SquareGrid implements Grid {

    private final List<String> cells;
    private final RuntimeException cellsSizeNotSquareException;

    public SquareGrid(final List<String> cells) {
        this(
                cells,
                new RuntimeException("Unable to show the grid because cells size is not a square number")
        );
    }

    SquareGrid(final List<String> cells, final RuntimeException cellsSizeNotSquareException) {
        this.cells = cells;
        this.cellsSizeNotSquareException = cellsSizeNotSquareException;
    }


    @Override
    public <O> Media<O> show(final Media<O> media) {
        var size = Math.sqrt(this.cells.size());
        if (size != (int) size) {
            throw this.cellsSizeNotSquareException;
        }
        return this.show((int) size, media);

    }

    private <O> Media<O> show(final int size, final Media<O> media) {
        var result = media;
        for (var row = 0; row < size; row++) {
            for (var col = 0; col < size; col++) {
                result = result.with(row, col, this.cells.get((row * size) + col));
            }
        }
        return result;
    }
}
