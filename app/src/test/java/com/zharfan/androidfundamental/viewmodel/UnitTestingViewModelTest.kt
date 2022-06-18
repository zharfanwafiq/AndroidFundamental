package com.zharfan.androidfundamental.viewmodel

import com.zharfan.androidfundamental.data.CuboidModel
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.*

class UnitTestingViewModelTest {

    private lateinit var viewModel: UnitTestingViewModel
    private lateinit var cuboidModel: CuboidModel

    private val dummyLength = 12.0
    private val dummyWidth = 7.0
    private val dummyHeight = 6.0

    private val expectedVolumeResult = 500.0
    private val expectedCircumferenceResult = 100.0
    private val expectedSurfaceAreaResult =  396.0

    @Before
    fun before(){
        cuboidModel = mock(CuboidModel::class.java)
        viewModel = UnitTestingViewModel(cuboidModel)
    }
    @Test
    fun getCircumference() {
        cuboidModel = CuboidModel()
        viewModel = UnitTestingViewModel(cuboidModel)
        viewModel.save(dummyWidth,dummyLength,dummyHeight)
        val circumference = viewModel.getCircumference()
        assertEquals(expectedCircumferenceResult,circumference, 0.0001)
    }

    @Test
    fun getSurfaceArea() {
        cuboidModel = CuboidModel()
        viewModel = UnitTestingViewModel(cuboidModel)
        viewModel.save(dummyWidth,dummyLength,dummyHeight)
        val surfaceArea = viewModel.getSurfaceArea()
        assertEquals(expectedSurfaceAreaResult,surfaceArea, 0.0001)
    }

    @Test
    fun getVolume() {
        cuboidModel = CuboidModel()
        viewModel = UnitTestingViewModel(cuboidModel)
        viewModel.save(dummyWidth,dummyLength,dummyHeight)
        val volume = viewModel.getVolume()
        assertEquals(expectedVolumeResult,volume, 0.0001)
    }

    @Test
    fun save() {
    }

    @Test
    fun getMockVolume(){
        `when`(viewModel.getVolume()).thenReturn(expectedVolumeResult)
        val volume = viewModel.getVolume()
        verify(cuboidModel).getVolume()
        assertEquals(expectedVolumeResult, volume, 0.0001)
    }

    @Test
    fun getMockCircumference(){
        `when`(viewModel.getCircumference()).thenReturn(expectedCircumferenceResult)
        val circumference = viewModel.getCircumference()
        verify(cuboidModel).getCircumference()
        assertEquals(expectedCircumferenceResult, circumference, 0.0001)
    }

    @Test
    fun getMockSurfaceArea(){
        `when`(viewModel.getSurfaceArea()).thenReturn(expectedSurfaceAreaResult)
        val surfaceArea = viewModel.getSurfaceArea()
        verify(cuboidModel).getSurfaceArea()
        assertEquals(expectedSurfaceAreaResult, surfaceArea, 0.0001)
    }
}