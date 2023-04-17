package io.github.raffaeleflorio.ndregrids.qute;

import io.github.raffaeleflorio.ndregrids.Grid;
import io.github.raffaeleflorio.ndregrids.Grids;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public final class QuteMedia implements Grids.Media<TemplateInstance> {

    private final Template template;
    private final Collection<Grid> grids;

    public QuteMedia(final Template template) {
        this(template, ConcurrentHashMap.newKeySet());
    }

    QuteMedia(final Template template, final Collection<Grid> grids) {
        this.template = template;
        this.grids = grids;
    }

    @Override
    public Grids.Media<TemplateInstance> with(final Grid grid) {
        this.grids.add(grid);
        return this;
    }

    @Override
    public TemplateInstance output() {
        var data = new DataGrids(
                this.grids
                        .stream()
                        .map(grid -> grid.show(
                                        new Grid.Media<DataGrid>() {

                                            private final DataGrid output = new DataGrid();

                                            @Override
                                            public Grid.Media<DataGrid> with(final Integer row, final Integer column, final String value) {
                                                this.output.rows.compute(row, (x, dataRow) -> {
                                                    if (dataRow == null) {
                                                        dataRow = new DataRow();
                                                    }
                                                    dataRow.columns.put(column, value.isEmpty() ? "⠀" : value);
                                                    return dataRow;
                                                });
                                                return this;
                                            }

                                            @Override
                                            public DataGrid output() {
                                                return this.output;
                                            }
                                        })
                                .output())
                        .collect(Collectors.toUnmodifiableList())
        );
        return this.template.instance().data(data);
    }

    public static final class DataGrids {

        private final Collection<DataGrid> grids;

        DataGrids(final Collection<DataGrid> grids) {
            this.grids = grids;
        }

        public Collection<DataGrid> getGrids() {
            return this.grids;
        }
    }

    public static final class DataGrid {

        private final String id;
        private final ConcurrentMap<Integer, DataRow> rows;

        DataGrid() {
            this(UUID.randomUUID().toString(), new ConcurrentHashMap<>());
        }

        DataGrid(final String id, final ConcurrentMap<Integer, DataRow> rows) {
            this.id = id;
            this.rows = rows;
        }

        public List<DataRow> getRows() {
            return this.rows
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toUnmodifiableList());
        }

        public String getId() {
            return this.id;
        }
    }

    public static final class DataRow {

        private final ConcurrentMap<Integer, String> columns;

        DataRow() {
            this(new ConcurrentHashMap<>());
        }

        DataRow(final ConcurrentMap<Integer, String> columns) {
            this.columns = columns;
        }

        public List<String> getColumns() {
            return this.columns
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toUnmodifiableList());
        }
    }
}
