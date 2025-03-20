package com.gonzaloracergalan.portfolio.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.gonzaloracergalan.portfolio.domain.usecase.SetCurrentResumeIdUseCase
import com.gonzaloracergalan.portfolio.common.response.UseCaseResponse
import com.gonzaloracergalan.portfolio.ui.state.SetCurrentResumeIdState
import com.gonzaloracergalan.portfolio.ui.util.PortfolioViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class MainViewModel : PortfolioViewModel() {
    companion object {
        private val logger = LoggerFactory.getLogger("MainViewModel")
    }

    private val setCurrentResumeIdUseCase: SetCurrentResumeIdUseCase by inject()

    private var _setCurrentResumeIdState: MutableStateFlow<SetCurrentResumeIdState>
        = MutableStateFlow(SetCurrentResumeIdState.Loading)
    val setCurrentResumeIdState: StateFlow<SetCurrentResumeIdState> = _setCurrentResumeIdState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SetCurrentResumeIdState.Loading
    )

    fun setCurrentResumeId(resumeId: Long) = viewModelScope.launch {
        logger.trace("setCurrentResumeId: resumeId=$resumeId")
        _setCurrentResumeIdState.emit(SetCurrentResumeIdState.Loading)
        val response = setCurrentResumeIdUseCase(resumeId)
        logger.info("setCurrentResumeId: response=$response")
        _setCurrentResumeIdState.emit(
            when(response) {
                is UseCaseResponse.Loading -> SetCurrentResumeIdState.Loading
                is UseCaseResponse.NotData -> SetCurrentResumeIdState.NotData
                is UseCaseResponse.Error -> SetCurrentResumeIdState.Error(
                    getErrorCodeFromUseCaseErrorType(response.error)
                )
                is UseCaseResponse.Success<*> -> SetCurrentResumeIdState.Success
            }
        )
    }
}