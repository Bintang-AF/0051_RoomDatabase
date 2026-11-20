package com.example.prak9.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.prak9.repositori.RepositoriSiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull

class DetailViewModel(
    savedStateHandle: SavedStateHandle
    private val repositoriSiswa: RepositoriSiswa

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemIdarg)

    val uiDetailState : StateFlow<DetailSiswaUiState> =
        repositoriSiswa.getSiswaStream(idSiswa)
            .filterNotNull()
            .map {
                DetailSiswaUiState(detailSiswa = it.toDetailSiswa())
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                        initialValue = DetailSiswaUiState()
                    )

                suspend fun deleteSiswa(){
                    repositoriSiswa.deleteSiswa(uiDetailSiswa.detailSiswa.toSiswa())
                }


                )