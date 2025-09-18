# DriverFactory Automation Framework

A unified test automation framework that supports both web and mobile testing, enabling execution with BDD (Cucumber) and TestNG approaches. It leverages a single driver class for Android, iOS, Chrome, Firefox, and Edge, with a configurable switching mechanism via the config.properties file.

## 🚀 Features

- **Multi-Platform Testing**: Web (Chrome, Firefox, Edge) and Android mobile testing
- **BDD Framework**: Cucumber integration with Gherkin feature files
- **TestNg Framework**: TestNg intefration with annotations to control test case execution flow efficiently and Test suite execution.
- **Page Object Model**: Modular design with reusable page objects
- **Advanced Reporting**: ExtentReports with HTML output and TestNG integration
- **Data-Driven Testing**: Excel integration with Apache POI
- **Cross-Browser Support**: Chrome, Firefox, and Edge drivers included
- **Mobile Testing**: Appium integration for Android app automation

## 📋 Prerequisites

- **Java 8+**
- **Maven 3.6+**
- **Android SDK** (for mobile testing)
- **Appium Server** (for mobile testing)
- **Web Browsers**: Chrome, Firefox, Edge
- **Android device** with USB debugging enabled
- **IOS device** with USB debugging enabled


## 🛠️ Installation

### 1. Clone Repository
```bash
git clone <repository-url>
cd newproject
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Start Appium Server (for mobile testing)
```bash
appium --port 4723
```

## ⚙️ Configuration

### Application Configuration
Update `config.properties` for your environment:

```properties
# Web Testing
platform=chrome
url=https://invest.motilaloswal.com/

# Android Configuration
androidVersion=13
androidDevice=Pixel_6
androidUdid=97957054
Rise_app_package=com.mosl.mobile
Rise_app_activity=com.mosl.mobile.MainActivity
``

## 🏃♂️ Running Tests

### Execute All Tests
```bash
mvn test
```

### Execute Specific Test Class
```bash
mvn test -Dtest=demotest
```

### Execute Cucumber Features
```bash
mvn test -Dcucumber.options="--tags @tag1"
```

## 📁 Project Structure

```
newproject/
├── src/test/java/
│   ├── drivers/
│   │   └── DriverFactory.java          # WebDriver management
│   ├── nadeem/
│   │   └── demotest.java              # Test execution class
│   ├── pageobjects/
│   │   ├── HomePage.java              # Home page elements
│   │   └── LoginPage.java             # Login functionality
│   ├── stepDefs/
│   │   ├── GetQSD.java                # Step definitions
│   │   └── GetSD.java                 # Step definitions
│   ├── utils/
│   │   └── Commons.java               # Utility methods
│   └── config.properties              # Configuration file
├── src/test/resources/
│   └── features/
│       └── demotest.feature           # BDD feature files
├── web drivers/                       # Browser drivers
│   ├── chromedriver.exe
│   ├── geckodriver.exe
│   └── msedgedriver.exe
├── test-output/                       # TestNG reports
├── target/                           # Build outputs
├── pom.xml                          # Maven dependencies
└── README.md
```

## 🔧 Key Components

### DriverFactory
- Multi-platform WebDriver management (Web + Mobile)
- Configurable browser and device capabilities
- Thread-safe driver initialization

### Page Objects
- **LoginPage**: User authentication functionality
- **HomePage**: Main dashboard interactions

### BDD Features
- **Launch and Login**: Application startup and authentication
- **Global Search**: Search functionality testing
- **Order Placement**: Trading order operations

## 📊 Reporting

### ExtentReports
- HTML reports with detailed test execution logs
- Screenshot capture on test failures
- Test execution timeline and statistics

### TestNG Reports
- Built-in HTML and XML reports
- Test suite execution summary
- JUnit XML reports for CI/CD integration

## 🧪 Test Scenarios

### Sanity Testing
- Application launch verification
- User login functionality
- Global search operations
- Quote page navigation

### Regression Testing
- Order placement workflows
- F&O (Futures & Options) trading
- Order book validation
- Application cleanup

## 🔍 Dependencies

### Core Testing
- **Selenium WebDriver 4.13.0**: Web automation
- **Appium Java Client 8.5.1**: Mobile automation
- **TestNG 7.10.2**: Test framework

### BDD Framework
- **Cucumber Java 7.20.1**: BDD implementation
- **Cucumber TestNG 7.20.1**: TestNG integration

### Reporting & Utilities
- **ExtentReports 5.0.9**: Advanced reporting
- **Apache POI 5.4.0**: Excel data handling
- **Log4j 2.23.1**: Logging framework
- **Commons IO 2.15.1**: File operations

### Additional Tools
- **Lombok 1.18.36**: Code generation
- **JFreeChart 1.0.19**: Chart generation
- **Winium WebDriver 0.1.0**: Windows app testing

## 🚀 Getting Started

1. **Setup Environment**: Install Java, Maven, and required browsers
2. **Configure Devices**: Update device capabilities in config.properties
3. **Start Services**: Launch Appium server for mobile testing
4. **Run Tests**: Execute test suites using Maven commands
5. **View Reports**: Check test-output directory for detailed reports

## 🐛 Troubleshooting

### Common Issues

**Driver Not Found**
- Verify driver paths in config.properties
- Ensure drivers are compatible with browser versions

**Appium Connection Failed**
- Check Appium server is running on default port
- Verify Android device connection and USB debugging

**Element Not Found**
- Check implicit wait timeouts
- Verify element locators in page objects

## 📈 Best Practices

- Use Page Object Model for maintainable code
- Implement proper wait strategies
- Maintain separate test data files
- Follow BDD naming conventions
- Regular driver and dependency updates

## 🤝 Contributing

1. Fork the repository
2. Create feature branch
3. Implement changes with tests
4. Submit pull request with detailed description

---

**Built for comprehensive Motilal Oswal trading platform automation**