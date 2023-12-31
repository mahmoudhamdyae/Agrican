package com.theflankers.agrican.domain.use_case

import com.theflankers.agrican.domain.model.Disease
import com.theflankers.agrican.domain.repository.MainRepository
import javax.inject.Inject

class GetDiseaseUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(diseaseId: String): Disease {
//        return mainRepository.getDisease(diseaseId)

        return Disease(
            title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى",
            name = "تيكا",
            description = listOf(
                "تظهر بقع سوداء أو بنيةعلى الأوراق السفلية لنبات الفول السودانى",
                "البقع قد تكون صغيرة أو كبيرة و تتواجد بشكل منتشر أو متجمع"
            ),
            reasons = listOf(
                "جفاف أو نقص الماء: نقص الماء فى التربة يؤدى إلى ضعف النبات و ظهور تبقع الأوراق",
                "تربة غير مناسبة: تربة فقيرة بالعناصر الغذائية أو غير متوازنة تؤثر على صحة النبات و تزيد من احتمالية ظهور التيكا",
                "الأمراض الفطرية: بعض الفطريات المسببة للأمراض تسبب تبقع الأوراق فى الفول السودانى"
            ),
            effects = listOf(
                "تقليل النمو و ضعف النبات",
                "تأثير سلبى على إنتاجية النبات وجودة المحصول النهائى"
            ),
            cure = listOf(
                "ضمان رى منتظم ومناسب للنبات",
                "نحسين جودة التربة وتوفير العناصر الغذائية اللازمة",
                "استخدام مبيدات فطرية للسيطرة على الأمراض الفطرية",
                "إجراء مراقبة دورية للنباتات والتدخل المبكر فى حالة ظهور التيكا"
            )
        )
    }
}