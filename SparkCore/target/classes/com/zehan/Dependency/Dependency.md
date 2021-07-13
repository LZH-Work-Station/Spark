# RDD的依赖血缘关系

## 1. 依赖关系和血缘关系

![image-20210705152925502](E:\Javadream\Spark\SparkCore\src\main\scala\com\zehan\Dependency\Dependency.assets\image-20210705152925502.png)

只要存在 直接依赖或者间接依赖，他们就都算有血缘关系

## 2. 依赖的种类

### 1.oneToOneDependency(窄依赖)

新的RDD的一个分区的数据依赖于旧的RDD一个分区的数据

![image-20210705153159516](E:\Javadream\Spark\SparkCore\src\main\scala\com\zehan\Dependency\Dependency.assets\image-20210705153159516.png)



### 2. ShuffleDependency(宽依赖)

![image-20210705153226229](E:\Javadream\Spark\SparkCore\src\main\scala\com\zehan\Dependency\Dependency.assets\image-20210705153226229.png)

新的RDD依赖于多个分区的数据，分区间数据进行了shuffle操作，每有一个Shuffle依赖就会给RDD添加一个阶段，换句话说只有所有的分区的oneToOne任务都结束了，才能一起进行shuffle任务。所以每有一个shuffle依赖就会给RDD添加一个新的阶段



## RDD的任务划分

![image-20210705154106334](E:\Javadream\Spark\SparkCore\src\main\scala\com\zehan\Dependency\Dependency.assets\image-20210705154106334.png)

## 源码执行过程

![image-20210705155826909](E:\Javadream\Spark\SparkCore\src\main\scala\com\zehan\Dependency\Dependency.assets\image-20210705155826909.png)