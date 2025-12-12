package hw_3.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CreateBugPage {

    private final SelenideElement btnCreate = $x("//a[@id='create_link']").as("Кнопка сверху 'Создать' задачу");
    private final SelenideElement project = $x("//input[@id='project-field']").as("Поле ввода навзвания проекта");
    private final SelenideElement typeField = $x("//input[@id='issuetype-field']").as("Поле ввода типа задачи");
    private final SelenideElement labelField = $x("//input[@class='text long-field']").as("Поле ввода темы задачи");
    private final SelenideElement descriptionArea = $x("//div[@id='description-wiki-edit']//iframe").as("Поле ввода описания");
    private final SelenideElement firstBtnVisual = $x("//div[@id='description-wiki-edit']//li[@data-mode='wysiwyg']/button").as("Кнопка 'Визуальный' под описанием");
    private final SelenideElement secondBtnVisual = $x("//div[@id='environment-wiki-edit']//li[@data-mode='wysiwyg']/button").as("Кнопка 'Визуальный' под окружением");
    private final SelenideElement fixVersion = $x("//select[@id='fixVersions']//option[@value='10001']").as("Выбор версии version 2.0 в 'Исправить в версиях'");
    private final SelenideElement takeVersion = $x("//select[@id='versions']//option[@value='10001']").as("Выбор версии version 2.0 в 'Затронуты версии'");
    private final SelenideElement priorityField = $x("//input[@id='priority-field']").as("Поле выбора приоритета задачи");
    private final SelenideElement markTextArea = $x("//textarea[@id='labels-textarea']").as("Поле 'Метки'");
    private final SelenideElement environmentArea = $x("//div[@id='environment-wiki-edit']//iframe").as("Поле ввода окружения");
    private final SelenideElement relations = $x("//select[@id='issuelinks-linktype']/option[@value='is blocked by']").as("Поле 'Связанные задачи' выбор is blocked by");
    private final SelenideElement task = $x("//div[@id='issuelinks-issues-multi-select']/textarea").as("Поле 'Задача'");
    private final SelenideElement executor = $x("//button[@id='assign-to-me-trigger']").as("Кнопка 'Назначить меня'");
    private final SelenideElement linkEpic = $x("//input[@id='customfield_10100-field']").as("Поле ссылки на эпик");
    private final SelenideElement sprint = $x("//input[@id='customfield_10104-field']").as("Поле выбора спринта");
    private final SelenideElement serious = $x("//select[@class='select cf-select']/option[@value='10101']").as("Поле выбора серьезности бага 'Minor'");
    private final SelenideElement finishedCreate = $x("//input[@id='create-issue-submit']").as("Финишная кнопка создать задачу(баг)");
    private final SelenideElement linkFlag = $x("//a[@class='issue-created-key issue-link']").as("Гиперссылка перехода на только что созданную задачу в уведомлении");
    private final SelenideElement businessProc = $x("//a[@id='opsbar-transitions_more']").as("Выпадающий список 'Бизнес-процесс'");
    private final SelenideElement readyTask = $x("//aui-item-link[@id='action_id_31']").as("Кнопка Выполнено");
    private final SelenideElement flagEnd = $x("//div[@class='aui-message closeable aui-message-success aui-will-close']").as("Всплывающее уведомление о смены статуса таски");
    private final SelenideElement becomeTask = $x("//a[@id='action_id_11']").as("Кнопка Нужно сделать");
    private final SelenideElement taskInProcess = $x("//a[@id='action_id_21']").as("Кнопка В работе");

    private static final String TYPE_TASK = "Ошибка";

    public void bugHistory(String projectName, String title, String bodyDescription, String priority, String mark,
                           String bodyEnvironment, String taskLink, String sprintName) {
        btnCreate.click();
        project.shouldBe(visible, Duration.ofSeconds(10)).sendKeys(projectName + Keys.TAB);
        typeField.shouldBe(visible, Duration.ofSeconds(10)).sendKeys(Keys.CONTROL + "a");
        typeField.sendKeys(Keys.DELETE);
        typeField.setValue(TYPE_TASK);
        labelField.shouldBe(visible, enabled, interactable, clickable).setValue(title);
        if (firstBtnVisual.getAttribute("aria-pressed").equals("false")) {
            firstBtnVisual.click();
            firstBtnVisual.shouldBe(attribute("aria-pressed", "true"),
                    Duration.ofSeconds(10));
        }
        switchTo().frame(descriptionArea);
        SelenideElement body = $("body");
        body.shouldBe(visible, enabled);
        body.clear();
        body.setValue(bodyDescription);
        switchTo().defaultContent();
        fixVersion.click();
        priorityField.shouldBe(visible, Duration.ofSeconds(10)).sendKeys(Keys.CONTROL + "a");
        priorityField.sendKeys(Keys.DELETE);
        priorityField.setValue(priority);
        markTextArea.sendKeys(mark);
        if (secondBtnVisual.getAttribute("aria-pressed").equals("false")) {
            secondBtnVisual.click();
            secondBtnVisual.shouldBe(attribute("aria-pressed", "true"),
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
        linkEpic.click();
        linkEpic.shouldBe(attribute("aria-expanded", "true"), Duration.ofSeconds(10))
                .sendKeys(Keys.DOWN, Keys.ENTER); //выбор первого сверху эпика
        sprint.sendKeys(sprintName + Keys.DOWN + Keys.ENTER);
        serious.click();
        finishedCreate.click();
        linkFlag.shouldBe(visible, Duration.ofSeconds(10)).click();
        becomeTask.shouldBe(visible, Duration.ofSeconds(10)).click();
        flagEnd.shouldBe(visible, Duration.ofSeconds(10));
        flagEnd.shouldBe(hidden, Duration.ofSeconds(10)); // каждый раз жду, чтобы уведомление закрылось
        taskInProcess.shouldBe(visible, Duration.ofSeconds(10)).click();
        flagEnd.shouldBe(visible, Duration.ofSeconds(10));
        flagEnd.shouldBe(hidden, Duration.ofSeconds(10));
        businessProc.shouldBe(visible, Duration.ofSeconds(10)).click();
        readyTask.shouldBe(visible, Duration.ofSeconds(10)).click();
        flagEnd.shouldBe(visible, Duration.ofSeconds(10));
    }
}
