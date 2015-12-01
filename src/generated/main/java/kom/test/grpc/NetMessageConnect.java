// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: net_message.proto

package kom.test.grpc;

/**
 * Protobuf type {@code NetMessageConnect}
 */
public  final class NetMessageConnect extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:NetMessageConnect)
    NetMessageConnectOrBuilder {
  // Use NetMessageConnect.newBuilder() to construct.
  private NetMessageConnect(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private NetMessageConnect() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private NetMessageConnect(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry) {
    this();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw new RuntimeException(e.setUnfinishedMessage(this));
    } catch (java.io.IOException e) {
      throw new RuntimeException(
          new com.google.protobuf.InvalidProtocolBufferException(
              e.getMessage()).setUnfinishedMessage(this));
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return kom.test.grpc.NetMessageProto.internal_static_NetMessageConnect_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return kom.test.grpc.NetMessageProto.internal_static_NetMessageConnect_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            kom.test.grpc.NetMessageConnect.class, kom.test.grpc.NetMessageConnect.Builder.class);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  public static kom.test.grpc.NetMessageConnect parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kom.test.grpc.NetMessageConnect parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kom.test.grpc.NetMessageConnect parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static kom.test.grpc.NetMessageConnect parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static kom.test.grpc.NetMessageConnect parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static kom.test.grpc.NetMessageConnect parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static kom.test.grpc.NetMessageConnect parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static kom.test.grpc.NetMessageConnect parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static kom.test.grpc.NetMessageConnect parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static kom.test.grpc.NetMessageConnect parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(kom.test.grpc.NetMessageConnect prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code NetMessageConnect}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:NetMessageConnect)
      kom.test.grpc.NetMessageConnectOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return kom.test.grpc.NetMessageProto.internal_static_NetMessageConnect_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return kom.test.grpc.NetMessageProto.internal_static_NetMessageConnect_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              kom.test.grpc.NetMessageConnect.class, kom.test.grpc.NetMessageConnect.Builder.class);
    }

    // Construct using kom.test.grpc.NetMessageConnect.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return kom.test.grpc.NetMessageProto.internal_static_NetMessageConnect_descriptor;
    }

    public kom.test.grpc.NetMessageConnect getDefaultInstanceForType() {
      return kom.test.grpc.NetMessageConnect.getDefaultInstance();
    }

    public kom.test.grpc.NetMessageConnect build() {
      kom.test.grpc.NetMessageConnect result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public kom.test.grpc.NetMessageConnect buildPartial() {
      kom.test.grpc.NetMessageConnect result = new kom.test.grpc.NetMessageConnect(this);
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof kom.test.grpc.NetMessageConnect) {
        return mergeFrom((kom.test.grpc.NetMessageConnect)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(kom.test.grpc.NetMessageConnect other) {
      if (other == kom.test.grpc.NetMessageConnect.getDefaultInstance()) return this;
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      kom.test.grpc.NetMessageConnect parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (kom.test.grpc.NetMessageConnect) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:NetMessageConnect)
  }

  // @@protoc_insertion_point(class_scope:NetMessageConnect)
  private static final kom.test.grpc.NetMessageConnect DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new kom.test.grpc.NetMessageConnect();
  }

  public static kom.test.grpc.NetMessageConnect getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NetMessageConnect>
      PARSER = new com.google.protobuf.AbstractParser<NetMessageConnect>() {
    public NetMessageConnect parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      try {
        return new NetMessageConnect(input, extensionRegistry);
      } catch (RuntimeException e) {
        if (e.getCause() instanceof
            com.google.protobuf.InvalidProtocolBufferException) {
          throw (com.google.protobuf.InvalidProtocolBufferException)
              e.getCause();
        }
        throw e;
      }
    }
  };

  public static com.google.protobuf.Parser<NetMessageConnect> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NetMessageConnect> getParserForType() {
    return PARSER;
  }

  public kom.test.grpc.NetMessageConnect getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

