import java.util.List;

import order.OrderSteps;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

abstract public class OrderJUnitStory extends JUnitStory {

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration().useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE));
	}

	@Override
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(configuration(), new OrderSteps()).createCandidateSteps();
	}

}
