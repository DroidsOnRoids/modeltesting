package pl.droidsonroids.modeltesting.api;


import android.annotation.TargetApi;
import android.os.Build;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

import pl.droidsonroids.modeltesting.model.Contributor;

import static org.assertj.core.api.Assertions.assertThat;

public class PlainContributorsTest {

	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Test
	public void testParseHardcodedContributors() throws Exception {
		//given
		String json = "[\n" +
				"  {\n" +
				"    \"login\": \"koral--\",\n" +
				"    \"id\": 3340954,\n" +
				"    \"site_admin\": true\n" +
				"  },\n" +
				"  {\n" +
				"    \"login\": \"Wavesonics\",\n" +
				"    \"id\": 406473,\n" +
				"    \"site_admin\": false\n" +
				"  }\n" +
				"]\n";

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gsonBuilder.create();

		//when
		Contributor[] contributors;
		try (Reader reader = new BufferedReader(new StringReader(json))) {
			contributors = gson.fromJson(reader, Contributor[].class);
		}

		//then
		assertThat(contributors).hasSize(2);
		Contributor contributor = contributors[0];
		assertThat(contributor.login).isEqualTo("koral--");
		assertThat(contributor.siteAdmin).isTrue();
	}

	@Test
	public void testGson() {
		//given
		Gson gson = new Gson();
		//when
		String result = gson.fromJson("\"test\"", String.class);
		//then
		assertThat(result).isEqualTo("test");
	}
}