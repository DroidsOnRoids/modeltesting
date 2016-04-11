package pl.droidsonroids.modeltesting.api;

import org.junit.Rule;
import org.junit.Test;

import pl.droidsonroids.modeltesting.api.utils.JsonFileResource;
import pl.droidsonroids.modeltesting.api.utils.JsonParsingRule;
import pl.droidsonroids.modeltesting.model.Constants;
import pl.droidsonroids.modeltesting.model.Contributor;

import static org.assertj.core.api.Assertions.assertThat;

public class ContributorsTest {

@Rule public JsonParsingRule jsonParsingRule = new JsonParsingRule(Constants.GSON);

@Test
@JsonFileResource(fileName = "contributors.json", clazz = Contributor[].class)
public void testGetContributors() throws Exception {
	Contributor[] contributors = jsonParsingRule.getValue();
	assertThat(contributors).hasSize(2);
	assertThat(contributors[0].login).isEqualTo("koral--");
}

	@Test
	public void testSomethingElse() {
		assertThat(jsonParsingRule.getValue()).isNull();
	}
}