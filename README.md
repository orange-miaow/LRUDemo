# LRUDemo

This program can demonstrate the LRU algorithm. 
For example,

```
Access	|7	|0	|1	|2	|0	|3	|0	|4	|2	|3	|0	|3	|2	|1	|2	|0	|1	|7	|0	|1	|
Block 1	|7	|7	|7	|2	|2	|2	|2	|4	|4	|4	|0	|0	|0	|1	|1	|1	|1	|1	|1	|1	|
Block 2	|-1	|0	|0	|0	|0	|0	|0	|0	|0	|3	|3	|3	|3	|3	|3	|0	|0	|0	|0	|0	|
Block 3	|-1	|-1	|1	|1	|1	|3	|3	|3	|2	|2	|2	|2	|2	|2	|2	|2	|2	|7	|7	|7	|
