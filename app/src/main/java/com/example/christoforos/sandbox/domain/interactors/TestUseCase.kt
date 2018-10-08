package com.example.christoforos.sandbox.domain.interactors

import com.example.christoforos.sandbox.domain.dataSources.TestDataSource
import com.example.christoforos.sandbox.domain.models.TestResults


class TestUseCase(private val dataSource: TestDataSource) : UseCase<TestResults> {
//    override fun execute(observer: Observer<TestResults>) {
//        dataSource.getTestData()
//                .subscribeOn(Schedulers.io()) //todo obtain it with another way through interfaces
//                .observeOn(AndroidSchedulers.mainThread()) //todo obtain it with another way through interfaces
//                .subscribe(observer)
//    }

}