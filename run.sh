spark-submit \
--packages com.databricks:spark-csv_2.10:1.5.0 \
--class dev.leonardo.rifeli.processor.BigDataProcessor \
--master local[8] \
target/scala-2.11/bigdataprocessor_2.11-0.1.jar