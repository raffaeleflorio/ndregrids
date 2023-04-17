package io.github.raffaeleflorio.ndregrids.square;

import io.github.raffaeleflorio.ndregrids.Grid;
import io.github.raffaeleflorio.ndregrids.Grids;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class NdreGrids implements Grids {

    private final static Integer GRID_SIZE = 9;
    private final static Long EMPTY_COUNT = 3L;

    private final Integer count;
    private final Random random;

    public NdreGrids(final Integer count) {
        this(count, new Random());
    }

    NdreGrids(final Integer count, final Random random) {
        this.count = count;
        this.random = random;
    }

    @Override
    public <O> Media<O> show(final Media<O> media) {
        var result = media;
        for (var i = 0; i < count; i++) {
            result = result.with(this.grid());
        }
        return result;
    }

    private Grid grid() {
        return new SquareGrid(this.cells());
    }

    private List<String> cells() {
        var cells = this.randomCells();
        if (this.emptyCount(cells).equals(EMPTY_COUNT)) {
            return cells;
        } else {
            return this.cells();
        }
    }

    private List<String> randomCells() {
        return IntStream.range(0, GRID_SIZE)
                .mapToObj(i -> this.randomChar())
                .collect(Collectors.toUnmodifiableList());
    }

    private Long emptyCount(final List<String> cells) {
        return cells
                .stream()
                .filter(String::isEmpty)
                .count();
    }

    private String randomChar() {
        return List.of("/", "\\", "").get(this.random.nextInt(3));
    }
}
