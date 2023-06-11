package com.example.agrican.domain.use_case

import com.example.agrican.domain.model.Crop
import com.example.agrican.domain.model.Pest

class GetPest {

    suspend operator fun invoke(pestId: String): Pest {
        return Pest(
            title = "تبقع الأوراق السيركسبورى أو (التيكا) فى الفول السودانى",
            name = "Scirpophaga inceertulas-Chilo suppressalis",
            categories = listOf(
                "Order Lepidoptera (فراشات)",
                "Family Crambidae (العواكس)",
                "Superfamily Pyraloidea"
            ),
            mainHosts = listOf(
                Crop(name = "نبات الأرز\nOryza sativa"),
                Crop(name = "نبات الأرز\nOryza sativa")
            ),
            lifeCycle = listOf(
                "اليرقات: تتغذى على سيقان الأرز الشابة والأوراق",
                "البلابل (الفراشات): تضع بيضها على أوراق الأرز",
                "اليرقات الجديدة: تحفر فى سيقان النبات وتتغذى داخلها"
            ),
            damages = listOf(
                "القلب الميت: تسبب جفاف البرعم المركزى للشتلات وتموت بعد ذلك",
                "السنابل البيضاء: تتلف الحبوب فى المراكز المبكرة وتسبب تدهور جودة السنابل"
            ),
            symptoms = listOf(
                "وجود كتلة بيضاء بالقرب من طرف الورقة",
                "جفاف البراعم المركزية (القلب الميت)",
                "تصبح العناقيد الكاملة مجففة (السنابل البيضاء)",
                "تظهر بقع بيضاء طولية على أغماد الورقة"
            ),
            injurySeason = "يهاجم الأرز خلال جميع مراحل نموه، بدءًا من طور البادرة و حتى تكوين السنابل",
            controlTiming = listOf(
                "فى الأرز الصيفى: يبدأ المكافحة عند عمر 25 يوم وقبل وصول نسبة القلب الميت إلى 5%",
                "فى الأرز الشتوى: تبدأ المكافحة بعد حوالى 40 يومًا من الشتل وقبل وصول نسبة القلب الميت إلى 5%"
            )
        )
    }
}