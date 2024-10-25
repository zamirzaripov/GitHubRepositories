An Android app displaying a list of GitHub repositories with detailed views.

## Tech Stack
- **Single Activity** and **ViewBinding**
- **Kotlin**
- **Coroutines** for async operations
- **Retrofit** for network requests
- **Navigation Component** for navigation
- Clean architecture and MVVM

## Features
1. **Repositories List Screen**
   - Displays a list of repositories with basic info (`https://api.github.com/search/repositories?q=android`)
   - Supports pull-to-refresh
   - Auto-refreshes every minute while app is open

2. **Repository Details Screen**
   - Shows detailed info of selected repository (`https://api.github.com/repositories/{repo_id}`)

3. **Bottom Navigation**
   - Navigation with five tabs (first tab implemented)
   - Preserves scroll position when switching tabs

4. **Error Handling**
   - Shows error messages (Toast) for network or other issues.
