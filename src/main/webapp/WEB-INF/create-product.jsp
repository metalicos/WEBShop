<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
    <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
        <jsp:param name="pageName" value="EShop | Create Product"/>
        <jsp:param name="description" value=""/>
    </jsp:include>
    <link rel="stylesheet" href="../design/css/createProduct.css">
</head>

<body>
<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp" %>


<form class="container col-sm-10 col-md-6 my-4"
      action="controller?command=create-product" method="post"  enctype="multipart/form-data" >

    <h1 class="h3 text-center">
        <span class="fw-bold text-decoration">Додати товар</span>
    </h1>
    <h1 class="h5 text-danger">${failErrorMessage}</h1>
    <div class="form-group">
        <h1 class="h5">Назва товару</h1>
        <div class="d-md-flex d-sm-block align-content-center justify-content-between">
            <div class="col-md-5 py-2 py-sm-2 py-md-1">
                <input type="text" name="pNameUa" placeholder="Назва товару українською"
                       class="form-control input" />
            </div>
            <div class="col-md-5 py-2 py-sm-2 py-md-1">
                <input type="text" name="pNameEn" placeholder="Product name in English"
                       class="form-control input" />
            </div>
        </div>
    </div>

    <div class="form-group">
        <h1 class="h5">Опис товару українською</h1>
        <textarea name="pAboutUa" cols="30" rows="3" class="text-area w-100 text-field"
                  style="height: 100px;min-height: 40px;"></textarea>
    </div>
    <div class="form-group">
        <h1 class="h5">Product description</h1>
        <textarea name="pAboutEn" cols="30" rows="3" class="text-area w-100 text-field"
                  style="height: 100px;min-height: 40px;"></textarea>
    </div>


    <div class="form-group">
        <h1 class="h5">Колір товару</h1>
        <div class="d-md-flex d-sm-block align-content-center justify-content-between">
            <div class="col-md-5 py-2 py-sm-2 py-md-1">
                <input type="text" name="pColorUa" placeholder="Колір товару українською"
                       class="form-control input" />
            </div>
            <div class="col-md-5 py-2 py-sm-2 py-md-1">
                <input type="text" name="pColorEn" placeholder="Product color in English"
                       class="form-control input" />
            </div>
        </div>
    </div>

    <div class="form-group">
        <h1 class="h5">Розмір товару</h1>
        <div class="d-md-flex d-sm-block align-content-center justify-content-between">
            <h1 class="h3 text-start mx-2 text-dark">L:</h1>
            <div class="input-group">
                <div class="col-6">
                    <input type="number" placeholder="0.00" name="pSizeWidth" min="0" value="0" step="0.01"
                           class="form-control input"  style="height: 40px"/>
                </div>
                <select name="mSizeWidth" class="form-select rounded-3" style="height: 40px">
                    <option value="mm">mm/мм</option>
                    <option value="cm">cm/см</option>
                    <option value="m">m/m</option>
                </select>
            </div>
            <h1 class="h3 text-start mx-2 text-dark">W:</h1>
            <div class="input-group">
                <div class="col-6">
                    <input type="number" placeholder="0.00" name="pSizeHeight" min="0" value="0" step="0.01"
                           class="form-control input"  style="height: 40px"/>
                </div>
                <select name="mSizeHeight" class="form-select rounded-3" style="height: 40px">
                    <option value="mm">mm/мм</option>
                    <option value="cm">cm/см</option>
                    <option value="m">m/m</option>
                </select>
            </div>
            <h1 class="h3 text-start mx-2 text-dark">H:</h1>
            <div class="input-group">
                <div class="col-6">
                    <input type="number" placeholder="0.00" name="pSizeDepth" min="0" value="0" step="0.01"
                           class="form-control input"  style="height: 40px"/>
                </div>
                <select name="mSizeDepth" class="form-select rounded-3" style="height: 40px">
                    <option value="mm">mm/мм</option>
                    <option value="cm">cm/см</option>
                    <option value="m">m/m</option>
                </select>
            </div>
        </div>
    </div>

    <div class="form-group">
        <h1 class="h5">Виберіть категорію</h1>
        <select name="categoryId" class="form-select-md w-100" style="height: 40px;">
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}">${category.nameUa}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group d-flex align-content-center justify-content-start my-2">
        <h1 class="h5 align-self-center">Немає потрібної категорії?</h1>
        <a class="text-decoration-none text-dark btn btn-outline-secondary border-2 mx-4"
           href="controller?command=open-create-category"
           target="_blank">Добавити категорію</a>
    </div>

    <div class="form-group">
        <h1 class="h5">Кількість товарів, що додаються</h1>
        <div class="d-md-flex d-sm-block align-content-center justify-content-between">
            <input type="number" name="productAmount" placeholder="0" min="0" value="0" step="1"
                   class="form-control input" />
        </div>
    </div>

    <div class="py-2 justify-content-center">
        <div class="row py-2 justify-content-between">
            <div class="col-sm-12 col-md-6 w-100">
                <div class="form-group files img-picker">
                    <label>Головне фото</label>
                    <input type="file" name="photo1" class="form-control">
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="form-group files img-picker">
                    <label>Додатково</label>
                    <input type="file" name="photo2" class="form-control">
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="form-group files img-picker">
                    <label>Додатково</label>
                    <input type="file" name="photo3" class="form-control">
                </div>
            </div>
        </div>
        <input type="submit" class="btn btn-dark text-center py-2 my-4 w-100" value="Створити"/>
    </div>
</form>


<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-light.jsp" %>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp" %>



<script>
    (function ($) {

        $.fn.imagePicker = function (options) {

            // Define plugin options
            var settings = $.extend({
                // Input name attribute
                name: "",
                // Classes for styling the input
                class: "form-control btn btn-default btn-block",
                // Icon which displays in center of input
                icon: "glyphicon glyphicon-plus"
            }, options);

            // Create an input inside each matched element
            return this.each(function () {
                $(this).html(create_btn(this, settings));
            });

        };

        // Private function for creating the input element
        function create_btn(that, settings) {
            // The input icon element
            var picker_btn_icon = $('<i class="' + settings.icon + '"></i>');

            // The actual file input which stays hidden
            var picker_btn_input = $('<input type="file" name="' + settings.name + '" />');

            // The actual element displayed
            var picker_btn = $('<div class="' + settings.class + ' img-upload-btn"></div>')
                .append(picker_btn_icon)
                .append(picker_btn_input);

            // File load listener
            picker_btn_input.change(function () {
                if ($(this).prop('files')[0]) {
                    // Use FileReader to get file
                    var reader = new FileReader();

                    // Create a preview once image has loaded
                    reader.onload = function (e) {
                        var preview = create_preview(that, e.target.result, settings);
                        $(that).html(preview);
                    }

                    // Load image
                    reader.readAsDataURL(picker_btn_input.prop('files')[0]);
                }
            });

            return picker_btn
        };

        // Private function for creating a preview element
        function create_preview(that, src, settings) {

            // The preview image
            var picker_preview_image = $('<img src="' + src + '" class="img-responsive img-rounded" ' +
                'style="max-height: 300px;max-width: 300px;" alt="..."/>');

            // The remove image button
            var picker_preview_remove = $('<button class="d-block btn btn-dark"><small>Remove</small></button>');

            // The preview element
            var picker_preview = $('<div class="d-flex"></div>')
                .append(picker_preview_image)
                .append(picker_preview_remove);

            // Remove image listener
            picker_preview_remove.click(function () {
                var btn = create_btn(that, settings);
                $(that).html(btn);
            });

            return picker_preview;
        };

    }(jQuery));

    $(document).ready(function () {
        $('.img-picker').imagePicker({name: 'images'});
    })
</script>

</body>
</html>