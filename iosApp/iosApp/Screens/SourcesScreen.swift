//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Bryant dos Anjos on 02/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss
    
    var body: some View {
        NavigationStack {
            Text("Sources")
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
        }
    }
}
