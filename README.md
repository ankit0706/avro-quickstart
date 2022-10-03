# avro-quickstart
A Quick start guide to serialize and deserialize data using Apache Avro

# Intro
Apache Avro is a serialization format, similar to protocol buffer. One can say that the Avro schema is a bit more flexible than protobuf as in, it does not need to have a unique numeric identifier for each field. Another advantage, and perhaps the USP of Avache Avro is that it is not mandatory to generate java code from the schema. Avro provides generic classes by using which our custom java code can directly pick the schema and use it for writing data to a file. Avro claims to have a more lightweight payload with its generic class approach. A beta version is available currently which aims to make the encoding and decoding even faster.
