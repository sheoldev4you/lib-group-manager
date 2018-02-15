# JavaGroupManager

[![](https://jitpack.io/v/tfSheol/JavaGroupManager.svg)](https://jitpack.io/#tfSheol/JavaGroupManager)

## Repository

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

## Dependency
```xml
<dependency>
  <groupId>com.github.tfSheol</groupId>
  <artifactId>java-group-manager</artifactId>
  <version>XXX</version>
</dependency>
```

## Output
```
add : ACCESS_TWITTER
Binary path : 1000
[ACCESS_TWITTER]
add : TEST_DATA
Binary path : 1001000
[ACCESS_TWITTER, TEST_DATA]
======================================
add : SHOW_BLOG
Binary path : 1001100
[ACCESS_TWITTER, SHOW_BLOG, TEST_DATA]
======================================
del : ACCESS_TWITTER
Binary path : 1000100
[SHOW_BLOG, TEST_DATA]
======================================
```
