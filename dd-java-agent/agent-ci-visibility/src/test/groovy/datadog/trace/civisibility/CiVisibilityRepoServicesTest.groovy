package datadog.trace.civisibility

import datadog.trace.api.Config
import spock.lang.Specification

import java.nio.file.Paths

class CiVisibilityRepoServicesTest extends Specification {

  def "test get parent module name: #parentModuleName, #repoSubFolder, #serviceName"() {
    given:
    def config = Stub(Config)
    config.getCiVisibilityModuleName() >> parentModuleName
    config.getServiceName() >> serviceName

    def repoRoot = "/path/to/repo/root/"
    def path = Paths.get(repoRoot + repoSubFolder)

    expect:
    CiVisibilityRepoServices.getModuleName(config, repoRoot, path) == moduleName

    where:
    parentModuleName | repoSubFolder  | serviceName    | moduleName
    "parent-module"  | "child-module" | "service-name" | "parent-module"
    null             | "child-module" | "service-name" | "child-module"
    null             | ""             | "service-name" | "service-name"
  }
}
