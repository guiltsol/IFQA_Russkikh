package hw_3.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class CreateBugPage {

    private final SelenideElement btnCreate = $x("//a[@id='create_link']");
    private final SelenideElement project = $x("//input[@id='project-field']");
    private final SelenideElement typeField = $x("//input[@id='issuetype-field']");
    private final SelenideElement labelField = $x("//input[@class='text long-field']");
    private final SelenideElement descriptionArea = $x("//div[@id='description-wiki-edit']//iframe");
    private final SelenideElement firstBtnVisual = $x("//div[@id='description-wiki-edit']//li[@data-mode='wysiwyg']/button");
    private final SelenideElement secondBtnVisual = $x("//div[@id='environment-wiki-edit']//li[@data-mode='wysiwyg']/button");
    private final SelenideElement fixVersion = $x("//select[@id='fixVersions']//option[@value='10001']");
    private final SelenideElement takeVersion = $x("//select[@id='versions']//option[@value='10001']");
    private final SelenideElement priorityField = $x("//input[@id='priority-field']");
    private final SelenideElement markTextArea = $x("//textarea[@id='labels-textarea']");
    private final SelenideElement environmentArea = $x("//div[@id='environment-wiki-edit']//iframe");
    private final SelenideElement relations = $x("//select[@id='issuelinks-linktype']/option[@value='clones']");
    private final SelenideElement task = $x("//div[@id='issuelinks-issues-multi-select']/textarea");
    private final SelenideElement executor = $x("//button[@id='assign-to-me-trigger']");
    private final SelenideElement linkEpic = $x("//input[@id='customfield_10100-field']");
    private final SelenideElement sprint = $x("//input[@id='customfield_10104-field']");
    private final SelenideElement serious = $x("//select[@class='select cf-select']/option[@value='10101']");
    private final SelenideElement finishedCreate = $x("//input[@id='create-issue-submit']");
    private final SelenideElement linkFlag = $x("//a[@class='issue-created-key issue-link']");
    private final SelenideElement businessProc = $x("//a[@id='opsbar-transitions_more']");
    private final SelenideElement readyTask = $x("//aui-item-link[@id='action_id_31']");
    private final SelenideElement flagEnd = $x("//div[@class='aui-message closeable aui-message-success aui-will-close']");
    private final SelenideElement becomeTask = $x("//a[@id='action_id_11']");
    private final SelenideElement taskInProcess = $x("//a[@id='action_id_21']");

    private static final String TYPE_TASK = "Ошибка";

    public void bugHistory(String projectName, String title, String bodyDescription, String mark,
                           String bodyEnvironment, String taskLink, String epicLink, String sprintName) {
        btnCreate.click();
        project.shouldBe(visible, Duration.ofSeconds(10)).sendKeys(projectName + Keys.TAB);
        typeField.shouldBe(visible, Duration.ofSeconds(10)).sendKeys(TYPE_TASK + Keys.TAB);
        labelField.sendKeys(title);
        if (firstBtnVisual.getAttribute("aria-pressed").equals("false")){
            firstBtnVisual.click();
            firstBtnVisual.shouldBe(Condition.attribute("aria-pressed","true"),
                    Duration.ofSeconds(10));
        }
        switchTo().frame(descriptionArea);
        SelenideElement body = $("body");
        body.shouldBe(visible, enabled);
        body.clear();
        body.setValue(bodyDescription);
        switchTo().defaultContent();
        fixVersion.click();
        priorityField.click();
        markTextArea.sendKeys(mark);
        if (secondBtnVisual.getAttribute("aria-pressed").equals("false")){
            secondBtnVisual.click();
            secondBtnVisual.shouldBe(Condition.attribute("aria-pressed","false"),
                    Duration.ofSeconds(10));
        }
        switchTo().frame(environmentArea);
        SelenideElement secondBody = $("body");
        secondBody.shouldBe(visible, enabled);
        secondBody.clear();
        secondBody.setValue(bodyEnvironment);
        switchTo().defaultContent();
        takeVersion.click();
        relations.click();
        task.sendKeys(taskLink + Keys.TAB);
        executor.click();
        linkEpic.sendKeys(epicLink + Keys.DOWN + Keys.ENTER);
        sprint.sendKeys(sprintName + Keys.DOWN + Keys.ENTER);
        serious.click();
        finishedCreate.click();
        linkFlag.shouldBe(visible, Duration.ofSeconds(10)).click();
        becomeTask.shouldBe(visible,Duration.ofSeconds(10)).click();
        flagEnd.shouldBe(visible,Duration.ofSeconds(10));
        flagEnd.shouldBe(hidden,Duration.ofSeconds(10)); // каждый раз жду, чтобы уведомление закрылось
        taskInProcess.shouldBe(visible,Duration.ofSeconds(10)).click();
        flagEnd.shouldBe(visible,Duration.ofSeconds(10));
        flagEnd.shouldBe(hidden,Duration.ofSeconds(10));
        businessProc.shouldBe(visible,Duration.ofSeconds(10)).click();
        readyTask.shouldBe(visible,Duration.ofSeconds(10)).click();
        flagEnd.shouldBe(visible,Duration.ofSeconds(10));
    }
}
