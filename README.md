[DEPRECATED]
=
*Use [crypto-json](https://github.com/hmrc/crypto#crypto-json instead*

# Json-encryption

![](https://img.shields.io/github/v/release/hmrc/json-encryption)

A library that provides JSON encryption.

Instead of `Protected`, you may prefer to use `Sensitive` from [crypto](https://github.com/hmrc/crypto#sensitive) and the play formats from [crypto-json](https://github.com/hmrc/crypto#crypto-json). This is especially recommended where erasure is a problem (e.g. mongo codecs).

## Setup

Add the jar to the project dependencies:

```scala
resolvers += MavenRepository("HMRC-open-artefacts-maven2", "https://open.artefacts.tax.service.gov.uk/maven2")libraryDependencies += "uk.gov.hmrc" %% "json-encryption" % "[INSERT VERSION]"
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
