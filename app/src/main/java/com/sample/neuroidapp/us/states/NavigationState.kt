package com.sample.neuroidapp.us.states

sealed class NavigationState {
    object Idle : NavigationState()
    object NavigateToScore : NavigationState()
    object NavigateBack : NavigationState()
}