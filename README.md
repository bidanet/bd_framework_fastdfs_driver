
# bd_framework_fastdfs_driver
fastdfs 的上传文件驱动


使用方法：

maven  pom中加入

<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>


	<dependency>
	    <groupId>com.github.bidanet</groupId>
	    <artifactId>bd_framework_fastdfs_driver</artifactId>
	    <version>v0.1</version>
	</dependency>


[![](https://jitpack.io/v/bidanet/bd_framework_fastdfs_driver.svg)](https://jitpack.io/#bidanet/bd_framework_fastdfs_driver)


把fdfs_client.conf 这个配置文件放入资源文件夹下面（resource）目录下面

fastDfsFileDrive.uploadImage(inputStream , fileNmae);