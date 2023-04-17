document.querySelectorAll('.js-show-button').forEach(addShowButtonEventListeners);

function addShowButtonEventListeners(showButton) {
  showButton.addEventListener('click', e => show(e.target.dataset.ndregrid))
}

function show(id) {
  var ndregrid = document.querySelector(`#ndregrid-${id} table`);
  ndregrid.classList.remove('is-invisible');
  window.setTimeout(() => ndregrid.classList.add('is-invisible'), 1000);
}

export default {
  show
}