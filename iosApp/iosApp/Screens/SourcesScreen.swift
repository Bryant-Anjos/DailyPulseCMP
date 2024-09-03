//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Bryant dos Anjos on 02/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension SourcesScreen {
    @MainActor
    class SourcesViewModelWrapper: ObservableObject {
        let sourcesViewModel: SourcesViewModel
        
        init() {
            sourcesViewModel = SourcesInjector().sourcesViewModel
            sourcesState = sourcesViewModel.sourcesState.value
        }
        
        @Published var sourcesState: SourcesState
        
        func startObserving() {
            Task {
                for await sourcesS in sourcesViewModel.sourcesState {
                    self.sourcesState = sourcesS
                }
            }
        }
    }
}

struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    
    @ObservedObject private(set) var viewModel: SourcesViewModelWrapper
    
    var body: some View {
        NavigationStack {
            VStack {
                if viewModel.sourcesState.loading {
                    Loader()
                }
                
                if let error = viewModel.sourcesState.error {
                    ErrorMessage(message: error)
                }
                
                if !viewModel.sourcesState.sources.isEmpty {
                    ScrollView {
                        LazyVStack(spacing: 10) {
                            ForEach(viewModel.sourcesState.sources, id: \.self) { source in
                                SourceItemView(source: source)
                            }
                        }
                    }
                }
            }
            .navigationTitle("Sources")
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        dismiss()
                    } label: {
                        Text("Done").bold()
                    }
                }
            }
        }.onAppear(
            perform: self.viewModel.startObserving
        )
    }
}

struct SourceItemView: View {
    var source: Source
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(source.name)
                .font(.title)
                .fontWeight(.bold)
            Text(source.description_)
            Text("\(source.country) - \(source.language)")
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundStyle(.gray)
        }
        .padding(16)
    }
}
