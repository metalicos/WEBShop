<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%-- Голова --%>
<head>
  <jsp:include page="/WEB-INF/parts/head-metadata.jsp">
    <jsp:param name="pageName" value="EShop"/>
    <jsp:param name="description" value="Welcome to EShop. The best Ukrainian Internet shop. Fast delivery, best offer."/>
  </jsp:include>
  <link rel="stylesheet" href="design/css/index.css">
</head>

<body>

<%-- Навігаційне меню --%>
<%@ include file="/WEB-INF/parts/navigation.jsp"%>


<!-- Наповнення сторінки -->
<div class="wrapper">
  <!-- Категорія -->
  <div class="d-md-flex align-items-md-center">
    <div class="h3">Флюси для паяння</div>
    <div class="ml-auto d-flex align-items-center views">
      <!--            <span class="btn text-success"> <span class="fas fa-th px-md-2 px-1"></span>
                    <span>Grid view</span>
                 </span>
                <span class="btn"> <span class="fas fa-list-ul"></span>
                     <span class="px-md-2 px-1">List view</span>
                 </span>-->
      <span class="green-label px-md-2 px-1">428</span>
      <span class="text-muted">Товарів</span>
    </div>
  </div>
  <!-- Фльтри -->
  <div class="d-lg-flex align-items-lg-center pt-2">
    <div class="form-inline d-flex align-items-center my-2 mr-lg-2 radio bg-light border">
      <label
              class="options">За назвою A-Z <input type="radio" name="radio"><span class="checkmark"></span>
      </label>
      <label
              class="options">За назвою Z-A <input type="radio" name="radio" checked> <span
              class="checkmark"></span>
      </label>
      <label
              class="options">За зростанням ціни <input type="radio" name="radio" checked> <span
              class="checkmark"></span>
      </label>
      <label
              class="options">За спаданням ціни <input type="radio" name="radio" checked> <span
              class="checkmark"></span>
      </label>
      <label
              class="options">Спочатку найновіші <input type="radio" name="radio" checked> <span
              class="checkmark"></span>
      </label>
    </div>

  </div>
  <!-- Застосовані фільри -->
  <div class="d-sm-flex align-items-sm-center pt-2 clear">
    <div class="text-muted filter-label">Застосовані Фільтри:</div>
    <div class="green-label font-weight-bold p-0 px-1 mx-sm-1 mx-0 my-sm-0 my-2">Фільр1 <span
            class=" px-1 close">&times;</span></div>
    <div class="green-label font-weight-bold p-0 px-1 mx-sm-1 mx-0">Фільтр2 <span class=" px-1 close">&times;</span>
    </div>
  </div>

  <div class="filters">
    <button class="btn btn-success" type="button" data-toggle="collapse" data-target="#mobile-filter"
            aria-expanded="true" aria-controls="mobile-filter">Filter<span class="px-1 fas fa-filter"></span>
    </button>
  </div>

  <div id="mobile-filter">
    <div class="py-3">
      <h5 class="font-weight-bold">Категорії</h5>
      <ul class="list-group">
        <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center category">
          Активні флюси <span class="badge badge-primary badge-pill">328</span></li>
        <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center category">
          Не активні флюси <span class="badge badge-primary badge-pill">112</span></li>
        <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center category">
          Не змивні флюси<span class="badge badge-primary badge-pill">32</span></li>
        <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center category">
          Зроблено в Україні<span class="badge badge-primary badge-pill">48</span></li>
      </ul>
    </div>
    <div class="py-3">
      <h5 class="font-weight-bold">Бренди</h5>
      <form class="brand">
        <div class="form-inline d-flex align-items-center py-1"><label class="tick">СвійКамінь <input
                type="checkbox"> <span class="check"></span> </label></div>
        <div class="form-inline d-flex align-items-center py-1"><label class="tick">СортСерв <input
                type="checkbox" checked> <span class="check"></span> </label></div>
        <div class="form-inline d-flex align-items-center py-1"><label class="tick">КадмійПлюс <input
                type="checkbox" checked> <span class="check"></span> </label></div>
        <div class="form-inline d-flex align-items-center py-1"><label class="tick">Шалена Фабрика <input
                type="checkbox"> <span class="check"></span> </label></div>
        <div class="form-inline d-flex align-items-center py-1"><label class="tick">ФігЗнайко <input
                type="checkbox"> <span class="check"></span> </label></div>
      </form>
    </div>
    <div class="py-3">
      <h5 class="font-weight-bold">Рейтинг</h5>
      <form class="rating">
        <div class="form-inline d-flex align-items-center py-2"><label class="tick"><span
                class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                class="fas fa-star"></span> <input type="checkbox"> <span class="check"></span> </label>
        </div>
        <div class="form-inline d-flex align-items-center py-2"><label class="tick"> <span
                class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                class="far fa-star px-1 text-muted"></span> <input type="checkbox"> <span
                class="check"></span> </label></div>
        <div class="form-inline d-flex align-items-center py-2"><label class="tick"><span
                class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                class="fas fa-star"></span> <span class="far fa-star px-1 text-muted"></span> <span
                class="far fa-star px-1 text-muted"></span> <input type="checkbox"> <span
                class="check"></span> </label></div>
        <div class="form-inline d-flex align-items-center py-2"><label class="tick"><span
                class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                class="far fa-star px-1 text-muted"></span> <span
                class="far fa-star px-1 text-muted"></span> <span
                class="far fa-star px-1 text-muted"></span> <input type="checkbox"> <span
                class="check"></span> </label></div>
        <div class="form-inline d-flex align-items-center py-2"><label class="tick"> <span
                class="fas fa-star"></span> <span class="far fa-star px-1 text-muted"></span> <span
                class="far fa-star px-1 text-muted"></span> <span
                class="far fa-star px-1 text-muted"></span> <span
                class="far fa-star px-1 text-muted"></span> <input type="checkbox"> <span
                class="check"></span> </label></div>
      </form>
    </div>
  </div>

  <div class="content py-md-0 py-3">
    <section id="sidebar">
      <div class="py-3">
        <h5 class="font-weight-bold">Категорії</h5>
        <ul class="list-group">
          <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center category">
            Активні флюси <span class="badge badge-primary badge-pill">328</span></li>
          <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center category">
            Не активні флюси <span class="badge badge-primary badge-pill">112</span></li>
          <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center category">
            Не змивні флюси<span class="badge badge-primary badge-pill">32</span></li>
          <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center category">
            Зроблено в Україні<span class="badge badge-primary badge-pill">48</span></li>
        </ul>
      </div>
      <div class="py-3">
        <h5 class="font-weight-bold">Бренди</h5>
        <form class="brand">
          <div class="form-inline d-flex align-items-center py-1"><label class="tick">СвійКамінь <input
                  type="checkbox"> <span class="check"></span> </label></div>
          <div class="form-inline d-flex align-items-center py-1"><label class="tick">СортСерв <input
                  type="checkbox" checked> <span class="check"></span> </label></div>
          <div class="form-inline d-flex align-items-center py-1"><label class="tick">КадмійПлюс <input
                  type="checkbox" checked> <span class="check"></span> </label></div>
          <div class="form-inline d-flex align-items-center py-1"><label class="tick">Шалена Фабрика <input
                  type="checkbox"> <span class="check"></span> </label></div>
          <div class="form-inline d-flex align-items-center py-1"><label class="tick">ФігЗнайко <input
                  type="checkbox"> <span class="check"></span> </label></div>
        </form>
      </div>
      <div class="py-3">
        <h5 class="font-weight-bold">Рейтинг</h5>
        <form class="rating">
          <div class="form-inline d-flex align-items-center py-2"><label class="tick"><span
                  class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                  class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                  class="fas fa-star"></span> <input type="checkbox"> <span class="check"></span> </label>
          </div>
          <div class="form-inline d-flex align-items-center py-2"><label class="tick"> <span
                  class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                  class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                  class="far fa-star px-1 text-muted"></span> <input type="checkbox"> <span
                  class="check"></span> </label></div>
          <div class="form-inline d-flex align-items-center py-2"><label class="tick"><span
                  class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                  class="fas fa-star"></span> <span class="far fa-star px-1 text-muted"></span> <span
                  class="far fa-star px-1 text-muted"></span> <input type="checkbox"> <span
                  class="check"></span> </label></div>
          <div class="form-inline d-flex align-items-center py-2"><label class="tick"><span
                  class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                  class="far fa-star px-1 text-muted"></span> <span
                  class="far fa-star px-1 text-muted"></span> <span
                  class="far fa-star px-1 text-muted"></span> <input type="checkbox"> <span
                  class="check"></span> </label></div>
          <div class="form-inline d-flex align-items-center py-2"><label class="tick"> <span
                  class="fas fa-star"></span> <span class="far fa-star px-1 text-muted"></span> <span
                  class="far fa-star px-1 text-muted"></span> <span
                  class="far fa-star px-1 text-muted"></span> <span
                  class="far fa-star px-1 text-muted"></span> <input type="checkbox"> <span
                  class="check"></span> </label></div>
        </form>
      </div>

    </section>
    <!-- Products Section -->
    <section id="products">
      <div class="container py-3">
        <div class="row">
          <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1">
            <div class="card"><img class="card-img-top" src="design/img/goods/fluxes/1.jpg">
              <div class="card-body">
                <h6 class="font-weight-bold pt-1">Флюс ПВ209х</h6>
                <div class="text-muted description">Флюс це є флюс бо він флюс</div>
                <div class="d-flex align-items-center product"><span class="fas fa-star"></span>
                  <span class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                          class="fas fa-star"></span> <span class="far fa-star"></span></div>
                <div class="d-flex align-items-center justify-content-between pt-3">
                  <div class="d-flex flex-column">
                    <div class="h6 font-weight-bold">36.99 Грн.</div>
                    <div class="text-muted rebate"><strike>48.56</strike></div>
                  </div>
                  <div class="btn btn-primary">Купити</div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 pt-md-0 pt-4">
            <div class="card"><img class="card-img-top" src="design/img/goods/fluxes/2.jpg">
              <div class="card-body">
                <h6 class="font-weight-bold pt-1">Флюс Ф3</h6>
                <div class="text-muted description">Флюс це є флюс бо він флюс</div>
                <div class="d-flex align-items-center product"><span class="fas fa-star"></span>
                  <span class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                          class="fas fa-star"></span> <span class="far fa-star"></span></div>
                <div class="d-flex align-items-center justify-content-between pt-3">
                  <div class="d-flex flex-column">
                    <div class="h6 font-weight-bold">36.99 Грн.</div>
                    <div class="text-muted rebate"><strike>48.56</strike></div>
                  </div>
                  <div class="btn btn-primary">Купити</div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 pt-lg-0 pt-4">
            <div class="card"><img class="card-img-top" src="design/img/goods/fluxes/3.jpg">
              <div class="card-body">
                <h6 class="font-weight-bold pt-1">Флюс Ф2</h6>
                <div class="text-muted description">Флюс це є флюс бо він флюс</div>
                <div class="d-flex align-items-center product"><span class="fas fa-star"></span>
                  <span class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                          class="fas fa-star"></span> <span class="far fa-star"></span></div>
                <div class="d-flex align-items-center justify-content-between pt-3">
                  <div class="d-flex flex-column">
                    <div class="h6 font-weight-bold">36.99 Грн.</div>
                    <div class="text-muted rebate"><strike>48.56</strike></div>
                  </div>
                  <div class="btn btn-primary">Купити</div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 pt-lg-4 pt-4">
            <div class="card"><img class="card-img-top" src="design/img/goods/fluxes/4.jpg">
              <div class="card-body">
                <h6 class="font-weight-bold pt-1">Флюс Ф1</h6>
                <div class="text-muted description">Флюс це є флюс бо він флюс</div>
                <div class="d-flex align-items-center product"><span class="fas fa-star"></span>
                  <span class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                          class="fas fa-star"></span> <span class="far fa-star"></span></div>
                <div class="d-flex align-items-center justify-content-between pt-3">
                  <div class="d-flex flex-column">
                    <div class="h6 font-weight-bold">36.99 Грн.</div>
                    <div class="text-muted rebate"><strike>48.56</strike></div>
                  </div>
                  <div class="btn btn-primary">Купити</div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 pt-lg-4 pt-4">
            <div class="card"><img class="card-img-top" src="design/img/goods/fluxes/5.jpg">
              <div class="card-body">
                <h6 class="font-weight-bold pt-1">Флюс Ф1</h6>
                <div class="text-muted description">Флюс це є флюс бо він флюс</div>
                <div class="d-flex align-items-center product"><span class="fas fa-star"></span>
                  <span class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                          class="fas fa-star"></span> <span class="far fa-star"></span></div>
                <div class="d-flex align-items-center justify-content-between pt-3">
                  <div class="d-flex flex-column">
                    <div class="h6 font-weight-bold">36.99 Грн.</div>
                    <div class="text-muted rebate"><strike>48.56</strike></div>
                  </div>
                  <div class="btn btn-primary">Купити</div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 pt-lg-4 pt-4">
            <div class="card"><img class="card-img-top" src="design/img/goods/fluxes/6.jpg">
              <div class="card-body">
                <h6 class="font-weight-bold pt-1">Флюс Ф1</h6>
                <div class="text-muted description">Флюс це є флюс бо він флюс</div>
                <div class="d-flex align-items-center product"><span class="fas fa-star"></span>
                  <span class="fas fa-star"></span> <span class="fas fa-star"></span> <span
                          class="fas fa-star"></span> <span class="far fa-star"></span></div>
                <div class="d-flex align-items-center justify-content-between pt-3">
                  <div class="d-flex flex-column">
                    <div class="h6 font-weight-bold">36.99 Грн.</div>
                    <div class="text-muted rebate"><strike>48.56</strike></div>
                  </div>
                  <div class="btn btn-primary">Купити</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>

  <div class="wrapper d-flex justify-content-center">
    <a class="btn page-link" href="#">&#8882</a>
    <a class="btn page-link" href="#">1</a>
    <a class="btn page-link" href="#">2</a>
    <a class="btn page-link" href="#">3</a>
    <a class="btn page-link" href="#">&#8883</a>
  </div>
</div>

<%-- Футер --%>
<%@ include file="/WEB-INF/parts/footer-full.jsp"%>

<%-- Скрипти --%>
<%@ include file="/WEB-INF/parts/scripts.jsp"%>

</body>
</html>