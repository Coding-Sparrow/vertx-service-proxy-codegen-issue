package service.proxy.issue.subpackage;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import service.proxy.issue.TestInterface;

@VertxGen
@ProxyGen
public interface SubInterface {

  // This has an argument of TestInterface and this is not able to auto generate the proxy class
  @Fluent
  SubInterface interfaceWithDependentProxy(JsonObject input, Handler<AsyncResult<TestInterface>> testInterfaceHandler);
}
