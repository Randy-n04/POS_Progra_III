package pos.presentation.estadisticas;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;

import java.util.Arrays;
import java.util.List;

 class MatrixDataSet {
     List<String> rowkeys;
     List<String> colskeys;
     float[][] data;

     public MatrixDataSet(String[] rows, String[] cols, float[][] data) {
         rowkeys = Arrays.asList(rows);
         colskeys = Arrays.asList(cols);
         this.data = data;
     }
 }