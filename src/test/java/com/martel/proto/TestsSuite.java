package com.martel.proto;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.martel.proto.category.AllTests;
import com.martel.proto.category.IntegrationTests;
import com.martel.proto.category.UnitTests;

@RunWith(Categories.class)
@Categories.IncludeCategory({
	UnitTests.class,
	IntegrationTests.class,
})
@Suite.SuiteClasses(AllTests.class)
public class TestsSuite {
}