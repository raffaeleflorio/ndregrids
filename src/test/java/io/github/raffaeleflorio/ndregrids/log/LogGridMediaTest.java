package io.github.raffaeleflorio.ndregrids.log;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LogGridMediaTest {

    @Test
    void appends_in_order_each_value_with_its_coordinate() {
        var log = new LogGridMedia()
                .with(0, 0, "a")
                .with(0, 1, "b")
                .with(1, 1, "c")
                .with(0, 0, "d")
                .output();

        assertThat(log)
                .isEqualTo("0,0=a 0,1=b 1,1=c 0,0=d");
    }
}