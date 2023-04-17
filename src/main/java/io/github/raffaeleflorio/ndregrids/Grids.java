package io.github.raffaeleflorio.ndregrids;

public interface Grids {

    <O> Media<O> show(Media<O> media);

    interface Media<O> {

        Media<O> with(Grid grid);

        O output();
    }
}
