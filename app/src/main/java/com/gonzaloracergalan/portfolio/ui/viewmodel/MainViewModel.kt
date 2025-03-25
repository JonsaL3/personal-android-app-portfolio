package com.gonzaloracergalan.portfolio.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.gonzaloracergalan.portfolio.common.response.UseCaseResponse
import com.gonzaloracergalan.portfolio.domain.usecase.GetCurrentActiveSectionsUseCase
import com.gonzaloracergalan.portfolio.domain.usecase.SetCurrentResumeIdUseCase
import com.gonzaloracergalan.portfolio.ui.model.ActiveResumeSectionUi
import com.gonzaloracergalan.portfolio.ui.state.GetCurrentActiveSectionsState
import com.gonzaloracergalan.portfolio.ui.state.SetCurrentResumeIdState
import com.gonzaloracergalan.portfolio.ui.util.PortfolioViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.slf4j.LoggerFactory

class MainViewModel : PortfolioViewModel() {
    companion object {
        private val logger = LoggerFactory.getLogger("MainViewModel")
    }

    private val setCurrentResumeIdUseCase: SetCurrentResumeIdUseCase by inject()
    private val getCurrentActiveSectionsState: GetCurrentActiveSectionsUseCase by inject()

    private var _setCurrentResumeIdState: MutableStateFlow<SetCurrentResumeIdState> =
        MutableStateFlow(SetCurrentResumeIdState.Loading)
    val setCurrentResumeIdState: StateFlow<SetCurrentResumeIdState> =
        _setCurrentResumeIdState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = SetCurrentResumeIdState.Loading
        )

    val activeSectionsState: StateFlow<GetCurrentActiveSectionsState> =
        getCurrentActiveSectionsState().map {
            when (it) {
                is UseCaseResponse.Loading -> GetCurrentActiveSectionsState.Loading
                is UseCaseResponse.NotData -> GetCurrentActiveSectionsState.NotData
                is UseCaseResponse.Error -> GetCurrentActiveSectionsState.Error(
                    getErrorCodeFromUseCaseErrorType(it.error)
                )

                is UseCaseResponse.Success<*> -> GetCurrentActiveSectionsState.Success(
                    it.data as? Set<ActiveResumeSectionUi> ?: emptySet()
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = GetCurrentActiveSectionsState.Loading
        )

    fun setCurrentResumeId(resumeId: Long) = viewModelScope.launch {
        logger.trace("setCurrentResumeId: resumeId=$resumeId")
        _setCurrentResumeIdState.emit(SetCurrentResumeIdState.Loading)
        val response = setCurrentResumeIdUseCase(resumeId)
        logger.info("setCurrentResumeId: response=$response")
        _setCurrentResumeIdState.emit(
            when (response) {
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