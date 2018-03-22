# JavaGroupManager

[![jitpack_badge](https://jitpack.io/v/tfSheol/lib-group-manager.svg)](https://jitpack.io/#tfSheol/lib-group-manager)
[![codebeat badge](https://codebeat.co/badges/cfd2efe8-230a-447a-a123-4a7012423c9f)](https://codebeat.co/projects/github-com-tfsheol-lib-group-manager-master)
[![Build Status](https://travis-ci.org/tfSheol/lib-group-manager.svg?branch=master)](https://travis-ci.org/tfSheol/lib-group-manager)
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/1747/badge)](https://bestpractices.coreinfrastructure.org/projects/1747)

## Maven
### Repository

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

### Dependency
```xml
<dependency>
  <groupId>com.github.tfSheol</groupId>
  <artifactId>lib-group-manager</artifactId>
  <version>0.0.1</version>
</dependency>
```

## Gradle
### Repository

```json
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

### Dependency
```json
dependencies {
  compile 'com.github.tfSheol:lib-group-manager:0.0.1'
}
``` 

## Config Example
```json
[
  {
    "ACCESS_TWITTER": {
      "description": "Allow access to twitter account.",
      "type": "right",
      "level": 2
    }
  },
  {
    "SHOW_BLOG": {
      "description": "Permit to show blog articles.",
      "type": "right",
      "level": 1
    }
  },
  {
    "TEST_DATA": {
      "description": "Permit to test all data.",
      "type": "right",
      "level": 5
    }
  }
]
```

## Output (See ExampleTest.java)
```
Right (add) : ACCESS_TWITTER
Binary path : 1000
[ACCESS_TWITTER]
Right (add) : TEST_DATA
Binary path : 1001000
[ACCESS_TWITTER, TEST_DATA]
======================================
Right (add) : SHOW_BLOG
Binary path : 1001100
[ACCESS_TWITTER, SHOW_BLOG, TEST_DATA]
======================================
Right (delete) : ACCESS_TWITTER
Binary path : 1000100
[SHOW_BLOG, TEST_DATA]
======================================
```

## Todo
- [x] Lib base
- [ ] Migrate JSON support to Jackson lib
- [ ] Add examples
