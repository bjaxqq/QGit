# QGit

![QU-Microsoft Logo](assets/images/QGitLogo.png)

## Project Summary

This GUI application automates the process of converting local projects into GitHub repositories. It handles all Git and GitHub operations with a simple interface, perfect for developers who want to save time on repository setup.

## Team Contributions

### [Zakaria](https://github.com/zakariamer)
- Designed main application window (JFrame)
- Implemented title branding and UI styling
- Developed submit button and event handling
- Created form validation logic

### [Brooks](https://github.com/bjaxqq)
- Established GitHub repository with branch protection
- Built all frontend input forms and components
- Connected frontend to backend services
- Managed team collaboration workflow

### [Ben](https://github.com/bbailor)
- Implemented core backend functionality
- Integrated GitSubprocessClient and GitHubApiClient
- Designed and implemented the application logo
- Configured GitHub API authentication

## How to Run

### Requirements
- Java installed system-wide
- Git installed system-wide
- GitHub account with personal access token (repo permissions)
- External libraries (download separately):
  - [GitSubprocessClient.jar](https://github.com/CSC109/GitSubprocessClient/releases/tag/v0.0.12) (v0.0.12)
  - [GitHubApiClient.jar](https://github.com/CSC109/GitHubApiClient/releases/tag/v0.0.7) (v0.0.7)

### Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/bjaxqq/QGit.git
   ```
2. Download required JARs from the links above
3. Add JARs to your project's build path
4. Create GitHub personal access token with repo permissions

### Usage

1. Launch `Main.java`
2. Enter GitHub credentials when prompted
3. Complete all form fields:
    - Local project path
    - Repository name
    - Description
    - Visibility (public/private)
4. Click "Submit"
5. View your new repository URL upon completion
