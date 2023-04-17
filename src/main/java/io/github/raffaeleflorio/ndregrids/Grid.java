package io.github.raffaeleflorio.ndregrids;

public interface Grid {

    <O> Media<O> show(Media<O> media);

    interface Media<O> {

        Media<O> with(Integer row, Integer column, String value);

        O output();
    }
}
