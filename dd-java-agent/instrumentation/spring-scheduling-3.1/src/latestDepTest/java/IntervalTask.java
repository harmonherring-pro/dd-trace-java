import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IntervalTask implements Runnable {

  private final CountDownLatch latch = new CountDownLatch(1);

  @Scheduled(fixedRate = 5000, scheduler = "tracingTaskScheduler")
  @Override
  public void run() {
    latch.countDown();
  }

  public void blockUntilExecute() throws InterruptedException {
    latch.await(5, TimeUnit.SECONDS);
  }
}
