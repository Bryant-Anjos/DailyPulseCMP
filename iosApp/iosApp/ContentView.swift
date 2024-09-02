import SwiftUI
import shared

struct ContentView: View {
    @State private var shouldOpenAbout = false
    @State private var shouldOpenSources = false
    
	var body: some View {
        let articleScreen = ArticlesScreen(viewModel: .init())
        
        NavigationStack {
            articleScreen
                .toolbar {
                    Button {
                        shouldOpenAbout = true
                    } label: {
                        Label("About", systemImage: "info.circle")
                            .labelStyle(.titleAndIcon)
                    }
                    .popover(isPresented: $shouldOpenAbout) {
                        AboutScreen()
                    }
                }
                .toolbar {
                    Button {
                        shouldOpenSources = true
                    } label: {
                        Label("Sources", systemImage: "list.bullet.rectangle")
                            .labelStyle(.titleAndIcon)
                    }
                    .popover(isPresented: $shouldOpenSources) {
                        SourcesScreen()
                    }
                }
        }.refreshable {
            articleScreen.viewModel.articlesViewModel.getArticles(forceFetch: true)
        }
	}
}
